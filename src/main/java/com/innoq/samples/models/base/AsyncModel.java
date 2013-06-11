package com.innoq.samples.models.base;

import com.innoq.samples.async.AsynchronousExecutor;
import com.innoq.samples.cache.CacheKey;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;

public class AsyncModel<T> implements LoadableModel<T> {

    enum State {
        NEW,
        LOADING,
        DONE
    }

    private final ReentrantLock lock = new ReentrantLock();

    private final LoadableModel<T> target;

    @SpringBean
    private AsynchronousExecutor executor;

    private State state = State.NEW;

    private Future<T> future;

    // ----------------------------------------------------

    public static <S> AsyncModel<S> async(LoadableModel<S> model) {
        return new AsyncModel<S>(model).start();
    }

    // ----------------------------------------------------

    public AsyncModel(LoadableModel<T> target) {
        Injector.get().inject(this);
        this.target = target;
    }

    // ----------------------------------------------------

    public AsyncModel<T> start() {
        lock.lock();
        try {
            if (State.NEW.equals(state)) {
                future = executor.submit(this);
                state = State.LOADING;
            }
        } finally {
            lock.unlock();
        }
        return this;
    }

    public T load() {
        lock.lock();
        try {
            return target.getObject();
        } finally {
            state = State.DONE;
            lock.unlock();
        }
    }

    // ----------------------------------------------------

    @Override
    public T getObject() {
        lock.lock();
        try {
            switch (state) {
                case NEW:
                    return load();
                case LOADING:
                    return waitAndGet();
                case DONE:
                    return target.getObject();
                default:
                    throw new IllegalStateException("Unexpected state: " + state);
            }
        } finally {
            state = State.DONE;
            lock.unlock();
        }
    }

    @Override
    public void setObject(T object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public CacheKey key() {
        return target.key();
    }

    @Override
    public void detach() {
        target.detach();
        future = null;
    }

    // ----------------------------------------------------

    private T waitAndGet() {
        try {
            return future.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

}
