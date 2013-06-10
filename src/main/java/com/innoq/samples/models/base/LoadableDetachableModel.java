package com.innoq.samples.models.base;

import com.innoq.samples.cache.CacheKey;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.IModel;

public abstract class LoadableDetachableModel<T> implements IModel<T> {
	
    private T loaded;

    // ----------------------------------------------------

    public LoadableDetachableModel() {
        Injector.get().inject(this);
    }

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

    protected abstract CacheKey key();

    protected T getDefault() {
        return null;
    }

}