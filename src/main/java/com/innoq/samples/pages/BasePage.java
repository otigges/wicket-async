package com.innoq.samples.pages;

import com.innoq.samples.WasyncSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.flow.RedirectToUrlException;
import org.apache.wicket.request.flow.ResetResponseException;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class BasePage extends WebPage {

    public BasePage(PageParameters parameters) {
        super(parameters);
        init();
    }

    public BasePage() {
        init();
    }

    // ----------------------------------------------------

    protected void init() {
        if (needsAuthenticatedUser() && !isLoggedIn()) {
            redirectToInterceptPage(new LoginPage());
            return;
        }
        Link logoutLink = new Link("logout") {
            @Override
            public void onClick() {
                WasyncSession.get().invalidate();
                setResponsePage(LoginPage.class);
            }
        };
        logoutLink.setVisible(isLoggedIn());
        add(logoutLink);

        Label usernameLabel = new Label("username", getUsername());
        usernameLabel.setVisible(isLoggedIn());
        add(usernameLabel);
    }

    protected boolean isLoggedIn() {
        return WasyncSession.exists() && WasyncSession.get().getUsername() != null;
    }

    protected String getUsername() {
        if (isLoggedIn()) {
            return WasyncSession.get().getUsername();
        } else {
            return "- not logged in -";
        }
    }

    protected boolean needsAuthenticatedUser() {
        return true;
    }

}
