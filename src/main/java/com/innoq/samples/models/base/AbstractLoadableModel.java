package com.innoq.samples.models.base;

import org.apache.wicket.injection.Injector;

public abstract class AbstractLoadableModel<T> implements LoadableModel<T> {

    protected AbstractLoadableModel() {
        Injector.get().inject(this);
    }

    // ----------------------------------------------------

    @Override
    public LoadableModel<T> getChainedModel() {
        return null;
    }

    @Override
    public void setObject(T object) {
        throw new UnsupportedOperationException("Loadable models may not be set directly.");
    }

    @Override
    public void detach() {
    }

}
