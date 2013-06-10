package com.innoq.samples.components;

import com.innoq.samples.connectors.Project;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import java.util.List;

public class ProjectsPanel extends Panel {

    // ----------------------------------------------------

    public ProjectsPanel(String id, IModel<List<Project>> model) {
        super(id, model);

        ListView<Project> view = new ListView<Project>("projects", model) {
            @Override
            protected void populateItem(ListItem<Project> item) {
                Project project = item.getModelObject();
                item.add(new Label("id", project.getId()));
                item.add(new Label("title", project.getTitle()));
                item.add(new Label("customer", project.getCustomer()));
                item.add(new Label("phase", project.getPhase()));
                item.add(new Label("status", project.getStatus()));
            }
        };
        add(view);
    }

}
