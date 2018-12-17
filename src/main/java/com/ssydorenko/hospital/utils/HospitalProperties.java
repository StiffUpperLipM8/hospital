package com.ssydorenko.hospital.utils;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.time.LocalTime;


@Component
@PropertySource("classpath:constants.properties")
public class HospitalProperties {

    @Value("${working_day_start_hours}")
    private int workingDayStartHours;

    @Value("${working_day_start_minutes}")
    private int workingDayStartMinutes;

    @Value("${working_day_end_hours}")
    private int workingDayEndHours;

    @Value("${working_day_end_minutes}")
    private int workingDayEndMinutes;

    @Value("${average_appointment_time_minutes}")
    private int averageAppointmentTimeMinutes;


    public LocalTime getWorkingDayStartTime() {

        return LocalTime.of(workingDayStartHours, workingDayStartMinutes);
    }


    public LocalTime getWorkingDayEndTime() {

        return LocalTime.of(workingDayEndHours, workingDayEndMinutes);
    }


    public int getAverageAppointmentTimeMinutes() {

        return averageAppointmentTimeMinutes;
    }

}
