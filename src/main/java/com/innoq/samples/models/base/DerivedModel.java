package com.innoq.samples.models.base;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.IModel;

public abstract class DerivedModel<T, S>  implements IModel<T> {

    private IModel<S> targetModel;

    private S targetObject;

    private T loaded;

    // ----------------------------------------------------

    public DerivedModel(IModel<S> target) {
        Injector.get().inject(this);
        this.targetModel = target;
    }

    public DerivedModel(S target) {
        Injector.get().inject(this);
        this.targetObject = target;
    }

    // ----------------------------------------------------

    @Override
    public final T getObject() {
        if (loaded == null) {
            if (targetModel != null) {
                loaded = derive(targetModel.getObject());
            } else {
                loaded = derive(targetObject);
            }
        }
        if (loaded != null) {
            return loaded;
        } else {
            return getDefault();
        }
    }

    @Override
    public void setObject(T object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void detach() {
        loaded = null;
        if (targetModel != null) {
            targetModel.detach();
        }
    }

    // ----------------------------------------------------

    protected abstract T derive(S target);

    protected T getDefault() {
        return null;
    }

}
