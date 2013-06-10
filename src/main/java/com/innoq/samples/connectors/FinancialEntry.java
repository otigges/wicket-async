package com.innoq.samples.connectors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;

public class FinancialEntry implements Serializable {

    private String project;

    private String customer;

    private BigDecimal revenue;

    private BigDecimal profit;

    // ----------------------------------------------------

    public FinancialEntry(String project, String customer, BigDecimal revenue, BigDecimal profit) {
        this.project = project;
        this.customer = customer;
        this.revenue = revenue;
        this.profit = profit;
    }

    // ----------------------------------------------------

    public String getProject() {
        return project;
    }

    public String getCustomer() {
        return customer;
    }

    public BigDecimal getRevenue() {
        return revenue;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    // ----------------------------------------------------

    @Override
    public String toString() {
        NumberFormat fmt = NumberFormat.getCurrencyInstance();
        return "FinancialEntry{" +
                "project='" + project + '\'' +
                ", customer='" + customer + '\'' +
                ", revenue='" + fmt.format(revenue.doubleValue()) + '\'' +
                ", profit='" + fmt.format(profit.doubleValue()) + '\'' +
                '}';
    }
}
