package com.innoq.samples.pages;

import com.innoq.samples.behaviors.LazyLoadBehavior;
import com.innoq.samples.components.FinancialDetailsPanel;
import com.innoq.samples.connectors.FinancialDetails;
import com.innoq.samples.models.FinancialDetailsModel;
import org.apache.wicket.model.IModel;

import static com.innoq.samples.models.base.AsyncModel.async;
import static com.innoq.samples.models.base.CachedModel.cached;

public class FinancialStatusPage extends BasePage {

    public FinancialStatusPage() {

        IModel<FinancialDetails> fnDetailsModel = async(cached(new FinancialDetailsModel(getUsername())));

        add(new FinancialDetailsPanel("financial-details", fnDetailsModel).add(new LazyLoadBehavior()));

    }

}
