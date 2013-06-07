package com.innoq.samples;

import com.innoq.samples.pages.DashBoardPage;
import com.innoq.samples.pages.FinancialStatusPage;
import com.innoq.samples.pages.LoginPage;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 * 
 * @see com.innoq.samples.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{    	
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return LoginPage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();

        mountPage("login", LoginPage.class);
        mountPage("dashboard", DashBoardPage.class);
        mountPage("financial-status", FinancialStatusPage.class);
	}

    @Override
    public Session newSession(Request request, Response response) {
        return new WasyncSession(request);
    }
}
