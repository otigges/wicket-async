package com.innoq.samples.pages;

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

public class DashBoardPage extends BasePage {

    public DashBoardPage() {

        String user = getUsername();

        IModel<List<Appointment>> apmtModel = new AppointmentModel(user);
        IModel<List<Project>> projectsModel = new ProjectsModel(user);
        IModel<FinancialStatus> fnStatModel = new FinancialStatusModel(user);

        add(new AppointmentPanel("appointments", apmtModel));
        add(new ProjectsPanel("projects", projectsModel));
        add(new FinancialStatusPanel("financial-status", fnStatModel));

        add(new BookmarkablePageLink("fs-details-link", FinancialStatusPage.class));

    }

}
