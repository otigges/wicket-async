package com.innoq.samples.components;

import com.innoq.samples.behaviors.LazyLoadBehavior;
import com.innoq.samples.models.base.AsyncModel;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AbstractAjaxTimerBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.time.Duration;

import java.text.NumberFormat;

public class LazyLoadingPlaceholderPanel extends Panel {

    private NumberFormat timeFormat = elapsedTimeFormat();

    private Component targetComponent;

    // ----------------------------------------------------

    public LazyLoadingPlaceholderPanel(final Component targetComponent) {
        super(targetComponent.getId());
        this.targetComponent = targetComponent;

        setOutputMarkupId(true);
        add(new AbstractAjaxTimerBehavior(refreshDuration()) {
            @Override
            protected void onTimer(AjaxRequestTarget target) {
                update(target);
            }
        });
        Label timer = new Label("timer", getTimerModel());
        timer.setOutputMarkupId(true);
        add(timer);
    }

    // ----------------------------------------------------

    private void update(AjaxRequestTarget target) {
        if (isDone()) {
            replaceWith(targetComponent);
            target.add(targetComponent);
            targetComponent.setVisible(true);
        } else {
            target.add(get("timer"));
        }
    }

    private boolean isDone() {
        AsyncModel asyncModel = LazyLoadBehavior.findAsyncModel(targetComponent.getDefaultModel());
        if (asyncModel == null) {
            throw new IllegalStateException("Found no async model in component: " + targetComponent);
        }
        return asyncModel.isDone();
    }

    // ----------------------------------------------------

    private IModel<String> getTimerModel() {
        return new AbstractReadOnlyModel<String>() {
            private long startTime = System.currentTimeMillis();
            @Override
            public String getObject() {
                double elapsed = (System.currentTimeMillis() - startTime) / 1000.;
                return timeFormat.format(elapsed) + " s";
            }
        };
    }

    private NumberFormat elapsedTimeFormat() {
        NumberFormat format = NumberFormat.getInstance(getLocale());
        format.setMaximumFractionDigits(2);
        return format;
    }

    private Duration refreshDuration() {
        return Duration.milliseconds(150 + Math.random() * 80);
    }

}
