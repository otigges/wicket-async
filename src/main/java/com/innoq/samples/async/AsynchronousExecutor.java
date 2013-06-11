package com.innoq.samples.async;

import com.innoq.samples.models.base.AsyncModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AsynchronousExecutor {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsynchronousExecutor.class);

    private ExecutorService executorService;

    // ----------------------------------------------------

    public AsynchronousExecutor() {
    }

    // ----------------------------------------------------

    public <T> Future<T> submit(final AsyncModel<T> model) {
        LOGGER.debug("Submitting new load task for {}.", model.key());
        return executorService.submit(new Callable<T>() {
            @Override
            public T call() throws Exception {
                long start = System.currentTimeMillis();
                LOGGER.debug("Start loading of {}.", model.key());
                T loaded = model.load();
                LOGGER.debug("Finished loading of {} in {}s.", model.key(), System.currentTimeMillis() - start);
                return loaded;
            }
        });
    }

    public void init() {
        LOGGER.info("Initializing new thread pool.");
        executorService = Executors.newFixedThreadPool(5);
    }

    public void destroy() {
        LOGGER.info("Shutting down thread pool.");
        executorService.shutdown();
    }
}
