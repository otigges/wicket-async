package com.innoq.samples.pages;

import com.innoq.samples.WasyncSession;
import com.innoq.samples.models.FinancialDetailsModel;
import com.innoq.samples.models.base.AsyncModel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;

import java.io.Serializable;

public class LoginPage extends BasePage {

	public LoginPage() {
        final Form<LoginData> form = new Form<LoginData>("form", new CompoundPropertyModel<LoginData>(new LoginData()));
        form.add(new FeedbackPanel("feedback"));
        form.add(new TextField<String>("username"));
        form.add(new PasswordTextField("password"));
        form.add(new Button("login") {
            @Override
            public void onSubmit() {
                onLogin(form.getModelObject().getUsername());
            }
        });
        add(form);
        add(new Label("version", getApplication().getFrameworkSettings().getVersion()));
    }

    // ----------------------------------------------------

    public void onLogin(String user) {

        AsyncModel.prefetch(new FinancialDetailsModel(user));

        WasyncSession.get().setUsername(user);
        setResponsePage(DashBoardPage.class);
    }

    // ----------------------------------------------------+

    @Override
    protected boolean needsAuthenticatedUser() {
        return false;
    }

    // ----------------------------------------------------

    class LoginData implements Serializable {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
