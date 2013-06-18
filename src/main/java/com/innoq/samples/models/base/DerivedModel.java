package com.innoq.samples.models.base;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.IModel;

public abstract class DerivedModel<T, S>  implements IModel<T> {

    private IModel<S> targetModel;

    private T loaded;

    // ----------------------------------------------------

    public DerivedModel(IModel<S> target) {
        Injector.get().inject(this);
        this.targetModel = target;
    }

    // ----------------------------------------------------

    @Override
    public final T getObject() {
        if (loaded == null) {
            loaded = derive(targetModel.getObject());
        }
        return loaded;
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

}
