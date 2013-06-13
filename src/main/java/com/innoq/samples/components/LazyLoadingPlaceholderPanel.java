package com.innoq.samples.components;

import com.innoq.samples.behaviors.LazyLoadBehavior;
import com.innoq.samples.models.base.AsyncModel;
import org.apache.wicket.ajax.AbstractAjaxTimerBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.time.Duration;
import org.apache.wicket.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LazyLoadingPlaceholderPanel extends Panel {

    private static final Logger LOGGER = LoggerFactory.getLogger(LazyLoadingPlaceholderPanel.class);

    private Component targetComponent;

    // ----------------------------------------------------

    public LazyLoadingPlaceholderPanel(final Component targetComponent) {
        super(targetComponent.getId());
        this.targetComponent = targetComponent;

        setOutputMarkupId(true);
        add(new AbstractAjaxTimerBehavior(Duration.milliseconds(200)) {
            @Override
            protected void onTimer(AjaxRequestTarget target) {
                update(target);
            }
        });
    }

    // ----------------------------------------------------

    private void update(AjaxRequestTarget target) {
        if (isDone()) {
            replaceWith(targetComponent);
            target.add(targetComponent);
            targetComponent.setVisible(true);
        } else {
            target.add(this);
        }
    }

    private boolean isDone() {
        AsyncModel asyncModel = LazyLoadBehavior.findAsyncModel(targetComponent.getDefaultModel());
        if (asyncModel == null) {
            throw new IllegalStateException("Found no async model in component: " + targetComponent);
        }
        return asyncModel.isDone();
    }

}
