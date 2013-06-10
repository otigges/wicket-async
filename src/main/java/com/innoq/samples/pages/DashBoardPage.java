package com.innoq.samples.pages;

import com.innoq.samples.components.AppointmentPanel;
import com.innoq.samples.components.FinancialStatusPanel;
import com.innoq.samples.components.ProjectsPanel;
import com.innoq.samples.models.AppointmentModel;
import com.innoq.samples.models.FinancialStatusModel;
import com.innoq.samples.models.ProjectsModel;

public class DashBoardPage extends BasePage {

    public DashBoardPage() {

        add(new AppointmentPanel("appointments", new AppointmentModel()));

        add(new ProjectsPanel("projects", new ProjectsModel()));

        add(new FinancialStatusPanel("financial-status", new FinancialStatusModel()));

    }
}
