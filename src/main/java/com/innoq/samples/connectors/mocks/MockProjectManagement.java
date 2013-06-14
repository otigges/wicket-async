package com.innoq.samples.connectors.mocks;

import com.innoq.samples.connectors.Project;
import com.innoq.samples.connectors.ProjectManagement;

import java.util.ArrayList;
import java.util.List;

public class MockProjectManagement extends BaseMock implements ProjectManagement {

    private final List<Project> data = new ArrayList<Project>();

    // ----------------------------------------------------

    public MockProjectManagement(int latency) {
        super(latency);
        initMock();
    }

    // ----------------------------------------------------

    @Override
    public List<Project> loadProjects(String username) {
        simulateLatency();
        return data;
    }

    // ----------------------------------------------------

    private void initMock() {
        for(int i=0; i < 9; i++) {
            String customer = aCompany();
            data.add(new Project(projectID(customer), aTitle(), customer, aPhase(), aStatus()));
        }
    }

    // ----------------------------------------------------

    public static void main(String[] args) {
        MockProjectManagement pm = new MockProjectManagement(0);
        List<Project> projects = pm.loadProjects("me");
        for (Project project : projects) {
            System.out.println(project);
        }
    }

}
