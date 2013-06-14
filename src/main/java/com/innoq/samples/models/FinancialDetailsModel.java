package com.innoq.samples.models;

import com.innoq.samples.cache.CacheKey;
import com.innoq.samples.connectors.FinancialDetails;
import com.innoq.samples.connectors.FinancialService;
import com.innoq.samples.connectors.FinancialStatus;
import com.innoq.samples.models.base.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class FinancialDetailsModel extends LoadableDetachableModel<FinancialDetails> {

    @SpringBean
    private FinancialService fs;

    private final String username;

    // ----------------------------------------------------

    public FinancialDetailsModel(String username) {
        this.username = username;
    }

    // ----------------------------------------------------

    @Override
    public CacheKey key() {
        return CacheKey.from(FinancialDetails.class, username);
    }

    @Override
    protected FinancialDetails load() {
        return fs.getFinancialDetails(username);
    }

}
