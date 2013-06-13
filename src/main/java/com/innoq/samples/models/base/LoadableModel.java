package com.innoq.samples.models.base;

import com.innoq.samples.cache.CacheKey;
import org.apache.wicket.model.IModel;

public interface LoadableModel<T> extends IModel<T> {

    CacheKey key();

    LoadableModel<T> getChainedModel();

}
