package com.innoq.samples.pages;

import com.innoq.samples.WasyncSession;
import com.innoq.samples.WicketApplication;
import com.innoq.samples.cache.ModelCache;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.text.NumberFormat;

public class BasePage extends WebPage {

    @SpringBean
    private ModelCache cache;

    // ----------------------------------------------------

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

        add(new Label("username", getUsername()).setVisible(isLoggedIn()));
        add(new BookmarkablePageLink("dashBoardLink", WicketApplication.get().getHomePage()).setVisible(isLoggedIn()));
        add(new Label("sessionSize", sessionSizeModel()));
        add(new Label("cacheSize", cacheSizeModel()));
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

    // ----------------------------------------------------

    private IModel<String> sessionSizeModel() {
        return new AbstractReadOnlyModel<String>() {
            @Override
            public String getObject() {
                if (WasyncSession.exists()) {
                    return WasyncSession.get().getSizeInBytes() + " b";
                } else {
                    return "none";
                }
            }
        };
    }

    private IModel<String> cacheSizeModel() {
        return new AbstractReadOnlyModel<String>() {
            @Override
            public String getObject() {
                NumberFormat format = NumberFormat.getInstance(getLocale());
                format.setMaximumFractionDigits(3);
                return format.format(cache.getCacheSize()) + " kb";
            }
        };
    }

}
