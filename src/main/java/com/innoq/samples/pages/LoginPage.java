package com.innoq.samples.pages;

import com.innoq.samples.WasyncSession;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;

import java.io.Serializable;

public class LoginPage extends BasePage {

	public LoginPage() {

		add(new Label("version", getApplication().getFrameworkSettings().getVersion()));

        final Model<LoginData> model = Model.of(new LoginData());

        Form form = new Form("form", new CompoundPropertyModel(model));

        form.add(new FeedbackPanel("feedback"));
        form.add(new TextField<String>("username"));
        form.add(new PasswordTextField("password"));

        form.add(new Button("login") {
            @Override
            public void onSubmit() {
                WasyncSession.get().setUsername(model.getObject().getUsername());
                setResponsePage(FinancialStatusPage.class);
            }
        });

        add(form);

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
