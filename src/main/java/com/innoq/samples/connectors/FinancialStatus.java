package com.innoq.samples.connectors;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class FinancialStatus {

    private List<FinancialEntry> entries = new ArrayList<FinancialEntry>();

    private Date effectiveData;

    // ----------------------------------------------------

    public FinancialStatus(Date effectiveData) {
        this.effectiveData = effectiveData;
    }

    // ----------------------------------------------------

    public void addEntries(FinancialEntry... entries) {
        Collections.addAll(this.entries, entries);
    }

    public List<FinancialEntry> getEntries() {
        return entries;
    }

    public Date getEffectiveData() {
        return effectiveData;
    }

    public BigDecimal getRevenue() {
        BigDecimal balance = new BigDecimal(0);
        for (FinancialEntry entry : entries) {
            balance = balance.add(entry.getRevenue());
        }
        return balance;
    }

    public BigDecimal getProfit() {
        BigDecimal balance = new BigDecimal(0);
        for (FinancialEntry entry : entries) {
            balance = balance.add(entry.getProfit());
        }
        return balance;
    }

    // ----------------------------------------------------

    @Override
    public String toString() {
        NumberFormat fmt = NumberFormat.getCurrencyInstance();
        return "FinancialStatus{" +
                ", effectiveData='" + effectiveData + '\'' +
                ", revenue='" + fmt.format(getRevenue().doubleValue()) + '\'' +
                ", profit='" + fmt.format(getProfit().doubleValue()) + '\'' +
                '}';
    }
}
