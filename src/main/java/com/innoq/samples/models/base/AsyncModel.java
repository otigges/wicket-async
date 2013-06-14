package com.innoq.samples.models.base;

import com.innoq.samples.async.AsynchronousExecutor;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;

public class AsyncModel<T> extends ChainedLoadableModel<T> {

    enum State {
        NEW,
        LOADING,
        DONE
    }

    private final ReentrantLock lock = new ReentrantLock();

    private State state = State.NEW;

    @SpringBean
    private AsynchronousExecutor executor;

    // ----------------------------------------------------

    public static <S> AsyncModel<S> async(LoadableModel<S> model) {
        return new AsyncModel<S>(model).start();
    }

    public static void prefetch(LoadableModel model) {
        new AsyncModel(model).prefetch();
    }

    // ----------------------------------------------------

    public AsyncModel(LoadableModel<T> target) {
        super(target);
    }

    // ----------------------------------------------------

    public AsyncModel<T> start() {
        lock.lock();
        try {
            if (State.NEW.equals(state)) {
                executor.submit(this);
                state = State.LOADING;
            }
        } finally {
            lock.unlock();
        }
        return this;
    }

    public void prefetch() {
        executor.prefetch(this);
    }

    public T load() {
        lock.lock();
        try {
            return getChainedModelObject();
        } finally {
            state = State.DONE;
            lock.unlock();
        }
    }

    public boolean isDone() {
        checkState();
        return State.DONE.equals(state);
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
                    waitUntilDone();
                    return getChainedModelObject();
                case DONE:
                    return getChainedModelObject();
                default:
                    throw new IllegalStateException("Unexpected state: " + state);
            }
        } finally {
            state = State.DONE;
            lock.unlock();
        }
    }

    // ----------------------------------------------------

    private void checkState() {
        if (State.LOADING.equals(state) && future().isDone()) {
            state = State.DONE;
        }
    }

    private void waitUntilDone() {
        try {
            future().get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private Future<T> future() {
        return executor.submit(this);
    }

}
