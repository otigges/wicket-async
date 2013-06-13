package com.innoq.samples.models.base;

import com.innoq.samples.cache.CacheKey;

public abstract class ChainedLoadableModel<T> extends AbstractLoadableModel<T> {

    private LoadableModel<T> target;

    // ----------------------------------------------------

    protected ChainedLoadableModel(LoadableModel<T> target) {
        this.target = target;
    }

    // ----------------------------------------------------

    @Override
    public CacheKey key() {
        return target.key();
    }

    @Override
    public LoadableModel<T> getChainedModel() {
        return target;
    }

    @Override
    public void detach() {
        target.detach();
    }

    // ----------------------------------------------------

    protected T getChainedModelObject() {
        return target.getObject();
    }

}
