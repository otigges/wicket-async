package com.innoq.samples.behaviors;

import com.innoq.samples.components.LazyLoadingPlaceholderPanel;
import com.innoq.samples.models.base.AsyncModel;
import com.innoq.samples.models.base.LoadableModel;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.model.IModel;

public class LazyLoadBehavior extends Behavior {

    private Component targetComponent;

    // ----------------------------------------------------

    public static AsyncModel findAsyncModel(IModel model) {
        if (model instanceof AsyncModel) {
            return (AsyncModel) model;
        } else if (model instanceof LoadableModel) {
            return findAsyncModel(((LoadableModel) model).getChainedModel());
        } else {
            return null;
        }
    }

    // ----------------------------------------------------

    @Override
    public void bind(Component component) {
        targetComponent = component;
        targetComponent.setOutputMarkupPlaceholderTag(true);
    }

    @Override
    public void onConfigure(Component component) {
        super.onConfigure(component);
        showPlaceholderIfStillLoading();
    }

    // ----------------------------------------------------

    protected Component newLazyLoadPlaceholder() {
        return new LazyLoadingPlaceholderPanel(targetComponent);
    }

    // ----------------------------------------------------

    private void showPlaceholderIfStillLoading() {
        AsyncModel async = findAsyncModel(targetComponent.getDefaultModel());
        if (async != null && !async.isDone()) {
            targetComponent.setVisible(false);
            displayLazyLoadingPlaceholder();
        }
    }

    private void displayLazyLoadingPlaceholder() {
        targetComponent.replaceWith(newLazyLoadPlaceholder());
    }

}
