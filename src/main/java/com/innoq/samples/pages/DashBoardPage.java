package com.innoq.samples.pages;

import com.innoq.samples.components.AppointmentPanel;
import com.innoq.samples.models.AppointmentModel;

public class DashBoardPage extends BasePage {

    public DashBoardPage() {

        add(new AppointmentPanel("appointments", new AppointmentModel()));

    }
}
