package com.sentriz.health.web.rest.vm;

import java.util.List;

import com.sentriz.health.domain.BloodPressure;

public class BloodPressureByPeriod {
    private String period;
    private List<BloodPressure> readings;

    public BloodPressureByPeriod(String period, List<BloodPressure> readings) {
        this.period = period;
        this.readings = readings;
    }

    public String getPeriod() {
        return period;
    }

    public List<BloodPressure> getReadings() {
        return readings;
    }

    @Override
    public String toString() {
        return "BloodPressureByPeriod{" +
            "period='" + period + '\'' +
            ", readings=" + readings +
            '}';
    }
}
