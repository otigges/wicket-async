package com.innoq.samples.behaviors;

import com.innoq.samples.components.LazyLoadingPlaceholderPanel;
import com.innoq.samples.models.base.AsyncModel;
import com.innoq.samples.models.base.LoadableModel;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.model.IModel;

public class LoadInBackgroundBehavior extends Behavior {

    @Override
    public void onConfigure(Component component) {
        super.onConfigure(component);

        // 1st: Find the AsyncModel of the component.
        AsyncModel async = findAsyncModel(component.getDefaultModel());

        // 2nd: Check if it is not yet loaded
        if (async != null && !async.isDone()) {

            // 3rd: Make component invisible
            component.setVisible(false);

            // 4th: Replace the component with a placeholder
            component.replaceWith(
                    new LazyLoadingPlaceholderPanel(component)
            );
        }
    }

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


}
