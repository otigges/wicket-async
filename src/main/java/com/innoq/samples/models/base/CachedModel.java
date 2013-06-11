package com.innoq.samples.models.base;

import com.innoq.samples.cache.CacheKey;
import com.innoq.samples.cache.ModelCache;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class CachedModel<T> implements LoadableModel<T> {

    private LoadableModel<T> target;

    private T attached;

    @SpringBean
    private ModelCache cache;

    // ----------------------------------------------------

    public static <T> CachedModel<T> cached(LoadableModel<T> model) {
        return new CachedModel<T>(model);
    }

    // ----------------------------------------------------

    public CachedModel(LoadableModel<T> target) {
        Injector.get().inject(this);
        this.target = target;
    }

    // ----------------------------------------------------

    @Override
    public T getObject() {
        if(attached != null) {
            return attached;
        }
        T cached = (T) cache.get(target.key());
        if (cached != null) {
            attached = cached;
            return cached;
        }
        attached= target.getObject();
        cache.put(target.key(), attached);
        return attached;
    }

    @Override
    public void setObject(T object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void detach() {
        attached = null;
        target.detach();
    }

    @Override
    public CacheKey key() {
        return target.key();
    }
}
