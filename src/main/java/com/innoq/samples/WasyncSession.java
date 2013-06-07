package com.innoq.samples;

import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

public class WasyncSession extends WebSession {

    private String username;

    // ----------------------------------------------------

    public static WasyncSession get() {
        return (WasyncSession) WebSession.get();
    }

    // ----------------------------------------------------

    public WasyncSession(Request request) {
        super(request);
    }

    // ----------------------------------------------------

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
