package com.innoq.samples.connectors;

import java.io.Serializable;

public class Project implements Serializable {

    private String id;

    private String title;

    private String customer;

    private String phase;

    private String status;

    // ----------------------------------------------------

    public Project(String id, String title, String customer, String phase, String status) {
        this.id = id;
        this.title = title;
        this.customer = customer;
        this.phase = phase;
        this.status = status;
    }

    public Project() {
    }

    // ----------------------------------------------------

    public String getId() {
        return id;
    }

    public Project setId(String id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Project setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getCustomer() {
        return customer;
    }

    public Project setCustomer(String customer) {
        this.customer = customer;
        return this;
    }

    public String getPhase() {
        return phase;
    }

    public Project setPhase(String phase) {
        this.phase = phase;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Project setStatus(String status) {
        this.status = status;
        return this;
    }

    // ----------------------------------------------------

    @Override
    public String toString() {
        return "Project{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", customer='" + customer + '\'' +
                ", phase='" + phase + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
