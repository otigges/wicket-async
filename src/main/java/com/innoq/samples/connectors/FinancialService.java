package com.innoq.samples.connectors;

public interface FinancialService {

    FinancialStatus getFinancialStatus(String username);

    FinancialDetails getFinancialDetails(String username);
}
