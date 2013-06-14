package com.innoq.samples.async;

import com.innoq.samples.cache.CacheKey;
import com.innoq.samples.cache.ModelCache;
import com.innoq.samples.models.base.AsyncModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AsynchronousExecutorImpl implements AsynchronousExecutor {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsynchronousExecutorImpl.class);

    private final Map<CacheKey, Future> runningTasks = new HashMap<CacheKey, Future>();

    private final ModelCache cache;

    private final ExecutorService executorService;

    // ----------------------------------------------------

    public AsynchronousExecutorImpl(ModelCache cache) {
        this.cache = cache;
        this.executorService = Executors.newFixedThreadPool(5);
    }

    // ----------------------------------------------------

    @Override
    public <T> Future<T> submit(AsyncModel<T> model) {
        CacheKey key = model.key();

         // 1st: check cache
        T value = (T) cache.get(key);
        if (value != null) {
            LOGGER.debug("Found model to load asynchronously in cache {}.", key);
            return new FinishedFuture<T>(value);
        }

        // 2nd: check running job
        Future<T> running = runningTasks.get(key);
        if (running != null) {
            LOGGER.debug("Detected already running task for {}.", key);
            return running;
        }

        // 3rd: submit new job
        Future<T> future = submitModel(model, false);
        runningTasks.put(key, future);
        return future;
    }

    public void prefetch(AsyncModel model) {
        CacheKey key = model.key();
        if (cache.contains(key) || runningTasks.containsKey(key)) {
            LOGGER.debug("Already loaded or queued {}.", key);
        } else {
            runningTasks.put(key, submitModel(model, true));
        }
    }

    @Override
    public void destroy() {
        LOGGER.info("Shutting down thread pool.");
        executorService.shutdown();
    }

    // ----------------------------------------------------

    private <T> Future<T> submitModel(final AsyncModel<T> model, final boolean cacheResult) {
        final CacheKey key = model.key();
        LOGGER.debug("Submitting new load task for {}.", key);
        return executorService.submit(new Callable<T>() {
            @Override
            public T call() throws Exception {
                long start = System.currentTimeMillis();
                LOGGER.debug("Start loading of {}.", key);
                T loaded = model.load();
                LOGGER.debug("Finished loading of {} in {}s.", key, System.currentTimeMillis() - start);
                runningTasks.remove(key);
                if (cacheResult) {
                    cache.put(key, loaded);
                }
                return loaded;
            }
        });
    }

}
