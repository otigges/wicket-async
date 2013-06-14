package com.innoq.samples.connectors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class FinancialDetails implements Serializable {

    private List<FinancialEntry> entries = new ArrayList<FinancialEntry>();

    private Date effectiveDate;

    // ----------------------------------------------------

    public FinancialDetails(Date effectiveData) {
        this.effectiveDate = effectiveData;
    }

    // ----------------------------------------------------

    public void addEntries(FinancialEntry... entries) {
        Collections.addAll(this.entries, entries);
    }

    public List<FinancialEntry> getEntries() {
        return entries;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
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

    public FinancialStatus createStatus() {
        return new FinancialStatus(effectiveDate, getRevenue(), getProfit());
    }

    // ----------------------------------------------------

    @Override
    public String toString() {
        NumberFormat fmt = NumberFormat.getCurrencyInstance();
        return "FinancialDetails {" +
                ", effectiveDate='" + effectiveDate + '\'' +
                ", revenue='" + fmt.format(getRevenue().doubleValue()) + '\'' +
                ", profit='" + fmt.format(getProfit().doubleValue()) + '\'' +
                '}';
    }
}
