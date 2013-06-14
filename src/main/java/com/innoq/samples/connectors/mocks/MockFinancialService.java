package com.innoq.samples.connectors.mocks;

import com.innoq.samples.connectors.FinancialDetails;
import com.innoq.samples.connectors.FinancialEntry;
import com.innoq.samples.connectors.FinancialService;
import com.innoq.samples.connectors.FinancialStatus;

import java.math.BigDecimal;
import java.util.Date;

import static java.lang.Math.random;

public class MockFinancialService extends BaseMock implements FinancialService {

    private FinancialDetails details = new FinancialDetails(new Date());

    // ----------------------------------------------------

    public MockFinancialService(int latency) {
        super(latency);
        initMock();
    }

    @Override
    public FinancialStatus getFinancialStatus(String username) {
        simulateLatency();
        return details.createStatus();
    }

    @Override
    public FinancialDetails getFinancialDetails(String username) {
        simulateLatency();
        return details;
    }

    // ----------------------------------------------------

    private void initMock() {
        for(int i=0; i < 12; i++) {
            String customer = aCompany();
            String projectID = projectID(customer);
            double v = random() * 5000. * 1000.;
            BigDecimal revenue = new BigDecimal(v);
            BigDecimal profit = profit(v);
            details.addEntries(new FinancialEntry(projectID, customer, revenue, profit));
        }
    }

    private BigDecimal profit(double revenue) {
        return new BigDecimal(revenue / 5 * (random() * 2. - 0.6));
    }

    // ----------------------------------------------------

    public static void main(String[] args) {
        MockFinancialService fs = new MockFinancialService(0);
        FinancialDetails details = fs.getFinancialDetails("me");
        FinancialStatus status = fs.getFinancialStatus("me");
        System.out.println(status);
        System.out.println(details);
        for (FinancialEntry entry : details.getEntries()) {
            System.out.println(entry);
        }
    }

}
