package com.innoq.samples.pages;

import com.innoq.samples.components.FinancialStatusDetailsPanel;
import com.innoq.samples.connectors.FinancialStatus;
import com.innoq.samples.models.FinancialStatusModel;
import org.apache.wicket.model.IModel;

public class FinancialStatusPage extends BasePage {

    public FinancialStatusPage() {

        IModel<FinancialStatus> fnStatModel = new FinancialStatusModel(getUsername());

        add(new FinancialStatusDetailsPanel("financial-status", fnStatModel));

    }

}
