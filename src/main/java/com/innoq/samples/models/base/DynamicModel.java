package com.innoq.samples.models.base;

public abstract class DynamicModel<T> extends AbstractLoadableModel<T> {
	
    private T loaded;

    // ----------------------------------------------------

    @Override
    public final T getObject() {
        if (loaded == null) {
            loaded = load();
        }
       return loaded;
    }

    @Override
    public void detach() {
        loaded = null;
    }

    // ----------------------------------------------------

    protected abstract T load();

}