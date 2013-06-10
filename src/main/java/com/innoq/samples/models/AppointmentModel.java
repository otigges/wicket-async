package com.innoq.samples.models;

import com.innoq.samples.cache.CacheKey;
import com.innoq.samples.connectors.Appointment;
import com.innoq.samples.connectors.CalenderManagement;
import com.innoq.samples.models.base.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.List;

public class AppointmentModel extends LoadableDetachableModel<List<Appointment>> {

    @SpringBean
    private CalenderManagement cm;

    // ----------------------------------------------------

    @Override
    protected List<Appointment> load() {
        return cm.loadAppointments("me");
    }

    @Override
    protected CacheKey key() {
        return CacheKey.from(Appointment.class, "me");
    }

}
