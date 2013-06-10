package com.innoq.samples.models;

import com.innoq.samples.connectors.Project;
import com.innoq.samples.connectors.ProjectManagement;
import com.innoq.samples.models.base.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.List;

public class ProjectsModel extends LoadableDetachableModel<List<Project>> {

    @SpringBean
    private ProjectManagement pm;

    // ----------------------------------------------------

    @Override
    protected List<Project> load() {
        return pm.loadProjects("me");
    }

}
