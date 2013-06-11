package com.innoq.samples.models;

import com.innoq.samples.cache.CacheKey;
import com.innoq.samples.connectors.FinancialService;
import com.innoq.samples.connectors.FinancialStatus;
import com.innoq.samples.models.base.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class FinancialStatusModel extends LoadableDetachableModel<FinancialStatus> {

    @SpringBean
    private FinancialService fs;

    private final String username;

    // ----------------------------------------------------

    public FinancialStatusModel(String username) {
        this.username = username;
    }

    // ----------------------------------------------------

    @Override
    public CacheKey key() {
        return CacheKey.from(FinancialStatus.class, username);
    }

    @Override
    protected FinancialStatus load() {
        return fs.getFinancialStatus(username);
    }

}
