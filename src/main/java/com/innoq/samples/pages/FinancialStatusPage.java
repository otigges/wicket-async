package com.innoq.samples.pages;

import com.innoq.samples.behaviors.LoadInBackgroundBehavior;
import com.innoq.samples.components.FinancialDetailsPanel;
import com.innoq.samples.connectors.FinancialDetails;
import com.innoq.samples.models.FinancialDetailsModel;
import com.innoq.samples.models.base.AsyncModel;
import com.innoq.samples.models.base.CachedModel;
import org.apache.wicket.model.IModel;

public class FinancialStatusPage extends BasePage {

    public FinancialStatusPage() {

        IModel<FinancialDetails> fnDetailsModel = AsyncModel.async(CachedModel.cached(new FinancialDetailsModel(getUsername())));

        add(new FinancialDetailsPanel("financial-details", fnDetailsModel).add(new LoadInBackgroundBehavior()));

    }

}
