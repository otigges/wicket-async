package com.innoq.samples.components;

import com.innoq.samples.connectors.FinancialStatus;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

public class FinancialStatusPanel extends Panel {

    public FinancialStatusPanel(String id, IModel<FinancialStatus> model) {
        super(id, model);

        add(new Label("date", new PropertyModel<FinancialStatus>(model, "effectiveDate")));
        add(new Label("revenue", new PropertyModel<FinancialStatus>(model, "revenue")));
        add(new Label("profit", new PropertyModel<FinancialStatus>(model, "profit")));
    }

}
