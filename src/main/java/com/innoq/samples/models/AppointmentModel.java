package com.innoq.samples.models;

import com.innoq.samples.cache.CacheKey;
import com.innoq.samples.connectors.Appointment;
import com.innoq.samples.connectors.CalenderManagement;
import com.innoq.samples.models.base.DynamicModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.List;

public class AppointmentModel extends DynamicModel<List<Appointment>> {

    @SpringBean
    private CalenderManagement cm;

    private final String username;

    // ----------------------------------------------------

    public AppointmentModel(String username) {
        this.username = username;
    }

    // ----------------------------------------------------

    @Override
    protected List<Appointment> load() {
        return cm.loadAppointments(username);
    }

    @Override
    public CacheKey key() {
        return CacheKey.from(Appointment.class, username);
    }

}
