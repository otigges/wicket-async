package com.innoq.samples.async;

import com.innoq.samples.models.base.AsyncModel;

import java.util.concurrent.Future;

public interface AsynchronousExecutor {

    <T> Future<T> submit(AsyncModel<T> model);

    void destroy();

}
