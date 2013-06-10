package com.innoq.samples.components;

import com.innoq.samples.connectors.FinancialEntry;
import com.innoq.samples.connectors.FinancialStatus;
import com.innoq.samples.models.base.DerivedModel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import java.util.List;

public class FinancialStatusPanel extends Panel {

    public FinancialStatusPanel(String id, IModel<FinancialStatus> model) {
        super(id, model);

        ListView<FinancialEntry> view = new ListView<FinancialEntry>("entries", entryModel(model)) {
            @Override
            protected void populateItem(ListItem<FinancialEntry> item) {
                FinancialEntry entry = item.getModelObject();
                item.add(new Label("project", entry.getProject()));
                item.add(new Label("customer", entry.getCustomer()));
                item.add(new Label("revenue", entry.getRevenue()));
                item.add(new Label("profit", entry.getProfit()));
            }
        };
        add(view);
    }

    // ----------------------------------------------------

    private IModel<List<FinancialEntry>> entryModel(IModel<FinancialStatus> model) {
        return new DerivedModel<List<FinancialEntry>, FinancialStatus>(model) {
            @Override
            protected List<FinancialEntry> derive(FinancialStatus target) {
                return target.getEntries();
            }
        };
    }

}
