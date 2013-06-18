package com.innoq.samples.models;

import com.innoq.samples.cache.CacheKey;
import com.innoq.samples.connectors.Project;
import com.innoq.samples.connectors.ProjectManagement;
import com.innoq.samples.models.base.DynamicModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.List;

public class ProjectsModel extends DynamicModel<List<Project>> {

    @SpringBean
    private ProjectManagement pm;

    private final String username;

    // ----------------------------------------------------

    public ProjectsModel(String username) {
        this.username = username;
    }

    // ----------------------------------------------------

    @Override
    public CacheKey key() {
        return CacheKey.from(Project.class, username);
    }

    @Override
    protected List<Project> load() {
        return pm.loadProjects(username);
    }

}
