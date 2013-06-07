package com.innoq.samples.connectors;

import java.util.List;

public interface CalenderManagement {

    List<Appointment> loadAppointments(String user);

}
