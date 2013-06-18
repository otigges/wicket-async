package com.innoq.samples.async;

import com.innoq.samples.models.base.AsyncModel;

import java.util.concurrent.Future;

public interface ExecutionService {

    <T> Future<T> submit(AsyncModel<T> model);

    void prefetch(AsyncModel model);

    void destroy();

}
