package com.innoq.samples.pages;

import com.innoq.samples.behaviors.LazyLoadBehavior;
import com.innoq.samples.components.AppointmentPanel;
import com.innoq.samples.components.FinancialStatusPanel;
import com.innoq.samples.components.ProjectsPanel;
import com.innoq.samples.connectors.Appointment;
import com.innoq.samples.connectors.FinancialStatus;
import com.innoq.samples.connectors.Project;
import com.innoq.samples.models.AppointmentModel;
import com.innoq.samples.models.FinancialStatusModel;
import com.innoq.samples.models.ProjectsModel;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.IModel;

import java.util.List;

import static com.innoq.samples.models.base.AsyncModel.async;
import static com.innoq.samples.models.base.CachedModel.cached;

public class DashBoardPage extends BasePage {

    public DashBoardPage() {

        String user = getUsername();

        IModel<List<Appointment>> apmtModel = async(cached(new AppointmentModel(user)));
        IModel<List<Project>> projectsModel = async(cached(new ProjectsModel(user)));
        IModel<FinancialStatus> fnStatModel = async(cached(new FinancialStatusModel(user)));

        add(new AppointmentPanel("appointments", apmtModel).add(new LazyLoadBehavior()));
        add(new ProjectsPanel("projects", projectsModel).add(new LazyLoadBehavior()));
        add(new FinancialStatusPanel("financial-status", fnStatModel).add(new LazyLoadBehavior()));

        add(new BookmarkablePageLink("fs-details-link", FinancialStatusPage.class));

    }

}
