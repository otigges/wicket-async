package com.innoq.samples.models.base;

import org.apache.wicket.injection.Injector;

public abstract class LoadableDetachableModel<T> extends AbstractLoadableModel<T> {
	
    private T loaded;

    // ----------------------------------------------------

    @Override
    public final T getObject() {
        if (loaded == null) {
            loaded = load();
        }
        if (loaded != null) {
            return loaded;
        } else {
            return getDefault();
        }
    }

    @Override
    public void detach() {
        loaded = null;
    }

    // ----------------------------------------------------

    protected abstract T load();

    protected T getDefault() {
        return null;
    }

}