package com.innoq.samples.components;

import com.innoq.samples.connectors.Appointment;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AppointmentPanel extends Panel {

    private DateFormat fmt = new SimpleDateFormat("HH:mm");

    // ----------------------------------------------------

    public AppointmentPanel(String id, IModel<List<Appointment>> model) {
        super(id, model);

        ListView<Appointment> view = new ListView<Appointment>("appointments", model) {
            @Override
            protected void populateItem(ListItem<Appointment> item) {
                item.add(new Label("day", new PropertyModel(item.getModel(), "day")));
                item.add(new Label("start", time(item.getModelObject().getStart())));
                item.add(new Label("end", time(item.getModelObject().getEnd())));
                item.add(new Label("customer", new PropertyModel(item.getModel(), "customer")));
                item.add(new Label("location", new PropertyModel(item.getModel(), "location")));
                item.add(new Label("subject", new PropertyModel(item.getModel(), "subject")));
            }
        };
        add(view);
    }

    // ----------------------------------------------------

    private String time(Date date) {
        return fmt.format(date);
    }

}
