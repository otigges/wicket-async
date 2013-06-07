package com.innoq.samples.connectors.mocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.random;
import static java.lang.Math.round;

public class BaseMock {

    private int latency;

    private final Map<String, String> customers = new HashMap<String, String>() {
        {
            put("Apple Inc.", "Cupertino");
            put("Microsoft", "Redmond");
            put("Deutsche Post AG", "Bonn");
            put("BMW AG", "Munich");
            put("Boing", "Seatle");
            put("Unicredit S.p.A.", "Rome");
            put("Ubisoft S.A.", "Rennes");
            put("Samsung", "Seoul");
            put("Philips N.V.", "Amsterdam");
            put("Sony", "Tokio");
            put("SAP", "Waldorf");
        }
    };

    private final List<String> subjects = Arrays.asList(
            "Review meeting",
            "Planning",
            "Retrospective",
            "Team event",
            "Looking for new projects",
            "Coffee and Cookies",
            "Emergency meeting",
            "Price negotiations",
            "Project kickoff",
            "Technical workshop",
            "Sell some stuff"
    );

    private final List<String> titles = Arrays.asList(
            "Redesign engine",
            "New prototype",
            "Documentation",
            "Enterprise Architecture Stuff",
            "Remove legacy apps",
            "Code metrics",
            "SOAP Workshops",
            "Green IT",
            "Test support",
            "Security testing",
            "General project support"
    );

    private final List<String> phases = Arrays.asList(
            "Inception",
            "Requirements Engineering",
            "Development",
            "Testing",
            "Certification",
            "Ramp up",
            "Ramp down"
    );

    private final List<String> status = Arrays.asList(
            "Dont' panic",
            "Panic",
            "Green",
            "Yellow",
            "Orange",
            "Melon",
            "Red",
            "Blue"
    );

    // ----------------------------------------------------

    public BaseMock(int latency) {
        this.latency = latency;
    }

    // ----------------------------------------------------

    protected String aCompany() {
        int idx = rnd(customers.size() - 1);
        return new ArrayList<String>(customers.keySet()).get(idx);
    }

    protected String aSubject() {
        return subjects.get(rnd(subjects.size() -1));
    }

    protected String aTitle() {
        return titles.get(rnd(titles.size() -1));
    }

    protected String aPhase() {
        return phases.get(rnd(phases.size() -1));
    }

    protected String aStatus() {
        return status.get(rnd(status.size() -1));
    }

    protected String locationOf(String customer) {
        return customers.get(customer);
    }

    protected String projectID(String customer) {
        return customer.substring(0, 3).toUpperCase() + "-" + rnd(1000);
    }

    protected void simulateLatency() {
        try {
            Thread.sleep(latency);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    protected int rnd(int max) {
        return (int) round(random() * max);
    }
}
