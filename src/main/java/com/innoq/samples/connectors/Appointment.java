package com.innoq.samples.connectors;

import java.util.Date;

public class Appointment {

    private Date start;

    private Date end;

    private String location;

    private String customer;

    private String subject;

    // ----------------------------------------------------

    public Appointment(Date start, Date end, String location, String customer, String subject) {
        this.start = start;
        this.end = end;
        this.location = location;
        this.customer = customer;
        this.subject = subject;
    }

    public Appointment() {
    }

    // ----------------------------------------------------


    public Date getStart() {
        return start;
    }

    public Appointment setStart(Date start) {
        this.start = start;
        return this;
    }

    public Date getEnd() {
        return end;
    }

    public Appointment setEnd(Date end) {
        this.end = end;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public Appointment setLocation(String location) {
        this.location = location;
        return this;
    }

    public String getCustomer() {
        return customer;
    }

    public Appointment setCustomer(String customer) {
        this.customer = customer;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public Appointment setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    // ----------------------------------------------------


    @Override
    public String toString() {
        return "Appointment{" +
                "start=" + start +
                ", end=" + end +
                ", location='" + location + '\'' +
                ", customer='" + customer + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
