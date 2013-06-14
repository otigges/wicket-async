package com.innoq.samples.connectors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class FinancialStatus implements Serializable {

    private Date effectiveDate;

    private BigDecimal revenue;

    private BigDecimal profit;

    // ----------------------------------------------------

    public FinancialStatus(Date effectiveDate, BigDecimal revenue, BigDecimal profit) {
        this.effectiveDate = effectiveDate;
        this.revenue = revenue;
        this.profit = profit;
    }

    // ----------------------------------------------------

    public Date getEffectiveDate() {
        return effectiveDate;
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
        return "FinancialStatus{" +
                ", effectiveDate='" + effectiveDate + '\'' +
                ", revenue='" + fmt.format(getRevenue().doubleValue()) + '\'' +
                ", profit='" + fmt.format(getProfit().doubleValue()) + '\'' +
                '}';
    }
}
