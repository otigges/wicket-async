package com.innoq.samples.pages;

import com.innoq.samples.components.FinancialDetailsPanel;
import com.innoq.samples.connectors.FinancialDetails;
import com.innoq.samples.models.FinancialDetailsModel;
import org.apache.wicket.model.IModel;

public class FinancialStatusPage extends BasePage {

    public FinancialStatusPage() {

        IModel<FinancialDetails> fnDetailsModel = new FinancialDetailsModel(getUsername());

        add(new FinancialDetailsPanel("financial-details", fnDetailsModel));

    }

}
