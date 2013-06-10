package com.innoq.samples.models;

import com.innoq.samples.cache.CacheKey;
import com.innoq.samples.connectors.FinancialService;
import com.innoq.samples.connectors.FinancialStatus;
import com.innoq.samples.models.base.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class FinancialStatusModel extends LoadableDetachableModel<FinancialStatus> {

    @SpringBean
    private FinancialService fs;

    // ----------------------------------------------------

    @Override
    protected FinancialStatus load() {
        return fs.getFinancialStatus("me");
    }

    @Override
    protected CacheKey key() {
        return CacheKey.from(FinancialStatus.class, "me");
    }

}
