package com.innoq.samples.models.base;

import org.apache.wicket.model.IModel;

public abstract class LoadableDetachableModel<T> implements IModel<T> {
	
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
    public void setObject(final T loaded) {
        this.loaded = loaded;
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