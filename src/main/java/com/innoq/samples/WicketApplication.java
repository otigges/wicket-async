package com.innoq.samples;

import com.innoq.samples.pages.DashBoardPage;
import com.innoq.samples.pages.FinancialStatusPage;
import com.innoq.samples.pages.LoginPage;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.settings.IRequestCycleSettings;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

public class WicketApplication extends WebApplication
{    	

	@Override
	public Class<? extends WebPage> getHomePage() {
		return DashBoardPage.class;
	}

	@Override
	public void init()
	{
		super.init();

        mountPage("login", LoginPage.class);
        mountPage("dashboard", DashBoardPage.class);
        mountPage("financial-status", FinancialStatusPage.class);

        getComponentInstantiationListeners().add(new SpringComponentInjector(this));

        getMarkupSettings().setDefaultMarkupEncoding("UTF-8");
        getRequestCycleSettings().setResponseRequestEncoding("UTF-8");
        getRequestCycleSettings().setRenderStrategy(IRequestCycleSettings.RenderStrategy.ONE_PASS_RENDER);
    }

    @Override
    public Session newSession(Request request, Response response) {
        return new WasyncSession(request);
    }

}
