package com.innoq.samples.models.base;

import com.innoq.samples.cache.ModelCache;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.io.Serializable;

public class CachedModel<T extends Serializable> implements IModel<T> {

    private LoadableDetachableModel<T> target;

    @SpringBean
    private ModelCache cache;

    // ----------------------------------------------------

    public CachedModel(LoadableDetachableModel<T> target) {
        Injector.get().inject(this);
        this.target = target;
    }

    // ----------------------------------------------------

    @Override
    public T getObject() {
        T cached = (T) cache.get(target.key());
        if (cached != null) {
            return cached;
        }
        T loaded = target.getObject();
        cache.put(target.key(), loaded);
        return loaded;
    }

    @Override
    public void setObject(T object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void detach() {
        target.detach();
    }

}
