package com.ssydorenko.hospital.utils;


import com.ssydorenko.hospital.domain.dto.VisitRequestDto;
import com.ssydorenko.hospital.utils.validator.DoctorServiceValidator;
import com.ssydorenko.hospital.utils.validator.VisitRequestServiceValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VisitRequestServiceValidatorTest {

    @Autowired
    private VisitRequestServiceValidator validator;

    @MockBean
    private DoctorServiceValidator doctorServiceValidator;

    @MockBean
    private HospitalProperties properties;


    @Before
    public void init() {

        when(properties.getWorkingDayStartTime()).thenReturn(LocalTime.of(9, 0));
        when(properties.getWorkingDayEndTime()).thenReturn(LocalTime.of(18, 0));
        when(properties.getAverageAppointmentTimeMinutes()).thenReturn(30);
    }


    @Test(expected = IllegalArgumentException.class)
    public void validationShouldFailWhenRequestedTimeIsBeforeDayStarts() {

        LocalDateTime requestedTime = LocalDateTime.of(2018, 12, 12, 23, 0, 0);
        VisitRequestDto dto = generateVisitRequestDto();
        dto.setRequestedDatetime(requestedTime);

        validator.validateAddRequest(dto);
    }


    @Test(expected = IllegalArgumentException.class)
    public void validationShouldFailWhenRequestedTimeIsAfterDayEnds() {

        LocalDateTime requestedTime = LocalDateTime.of(2018, 12, 12, 19, 0, 0);
        VisitRequestDto dto = generateVisitRequestDto();
        dto.setRequestedDatetime(requestedTime);

        validator.validateAddRequest(dto);
    }


    @Test(expected = IllegalArgumentException.class)
    public void validationShouldFailWhenRequestedTimeIsAfterDayEndsMinusAppointmentTime() {

        LocalDateTime requestedTime = LocalDateTime.of(2018, 12, 12, 17, 35, 0);
        VisitRequestDto dto = generateVisitRequestDto();
        dto.setRequestedDatetime(requestedTime);

        validator.validateAddRequest(dto);
    }


    private VisitRequestDto generateVisitRequestDto() {

        VisitRequestDto dto = new VisitRequestDto();
        dto.setDoctorId(1L);
        return dto;
    }
}
