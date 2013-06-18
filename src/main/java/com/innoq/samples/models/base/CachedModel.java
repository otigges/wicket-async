package com.innoq.samples.models.base;

import com.innoq.samples.cache.ModelCache;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class CachedModel<T> extends ChainedLoadableModel<T> {

    @SpringBean
    private ModelCache cache;

    private T attached;

    // ----------------------------------------------------

    public static <T> CachedModel<T> cached(LoadableModel<T> model) {
        return new CachedModel<T>(model);
    }

    // ----------------------------------------------------

    public CachedModel(LoadableModel<T> target) {
        super(target);
    }

    // ----------------------------------------------------

    @Override
    public T getObject() {
        if(attached != null) {
            return attached;
        }
        T cached = (T) cache.get(key());
        if (cached != null) {
            attached = cached;
            return cached;
        }
        attached = getChainedModelObject();
        cache.put(key(), attached);
        return attached;
    }

    // ----------------------------------------------------

    @Override
    public void detach() {
        super.detach();
        attached = null;
    }

}
