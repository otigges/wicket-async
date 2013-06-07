package com.innoq.samples.connectors.mocks;

import com.innoq.samples.connectors.Appointment;
import com.innoq.samples.connectors.CalenderManagement;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.lang.Math.random;

public class MockCalendarManagement extends BaseMock implements CalenderManagement {

    private final List<Appointment> data = new ArrayList<Appointment>();

    // ----------------------------------------------------

    public MockCalendarManagement(int latency) {
        super(latency);
        initMock();
    }

    // ----------------------------------------------------

    @Override
    public List<Appointment> loadAppointments(String user) {
        simulateLatency();
        return data;
    }

    // ----------------------------------------------------

    private void initMock() {
        int day = rnd(30);
        for (int i = 0; i < 15; i++) {
            addAppointment(day);
            day += 1 + (random() * 3);
        }
    }

    private void addAppointment(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2013, 7, day, 0, 0, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 8 + rnd(6));
        Date start = calendar.getTime();
        calendar.roll(Calendar.HOUR_OF_DAY, 1 + rnd(3));
        Date end = calendar.getTime();
        String customer = aCompany();
        data.add(new Appointment(start, end, locationOf(customer), customer, aSubject()));
    }

    // ----------------------------------------------------

    public static void main(String[] args) {
        MockCalendarManagement cm = new MockCalendarManagement(0);
        for (Appointment app : cm.loadAppointments("me")) {
            System.out.println(app);
        }
    }

}
