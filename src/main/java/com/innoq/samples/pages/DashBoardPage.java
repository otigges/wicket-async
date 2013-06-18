package com.innoq.samples.pages;

import com.innoq.samples.behaviors.LoadInBackgroundBehavior;
import com.innoq.samples.components.AppointmentPanel;
import com.innoq.samples.components.FinancialStatusPanel;
import com.innoq.samples.components.ProjectsPanel;
import com.innoq.samples.connectors.Appointment;
import com.innoq.samples.connectors.FinancialStatus;
import com.innoq.samples.connectors.Project;
import com.innoq.samples.models.AppointmentModel;
import com.innoq.samples.models.FinancialStatusModel;
import com.innoq.samples.models.ProjectsModel;
import com.innoq.samples.models.base.AsyncModel;
import com.innoq.samples.models.base.CachedModel;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.IModel;

import java.util.List;

public class DashBoardPage extends BasePage {

    public DashBoardPage() {

        String user = getUsername();

        IModel<List<Appointment>> apmtModel = AsyncModel.async(CachedModel.cached(new AppointmentModel(user)));
        IModel<List<Project>> projectsModel = AsyncModel.async(CachedModel.cached(new ProjectsModel(user)));
        IModel<FinancialStatus> fnStatModel = AsyncModel.async(CachedModel.cached(new FinancialStatusModel(user)));

        add(new AppointmentPanel("appointments", apmtModel).add(new LoadInBackgroundBehavior()));
        add(new ProjectsPanel("projects", projectsModel).add(new LoadInBackgroundBehavior()));
        add(new FinancialStatusPanel("financial-status", fnStatModel).add(new LoadInBackgroundBehavior()));

        add(new BookmarkablePageLink("fs-details-link", FinancialStatusPage.class));

    }

}
