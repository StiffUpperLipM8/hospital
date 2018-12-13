package com.ssydorenko.hospital.utils.validator;

import com.ssydorenko.hospital.db.repository.VisitRequestRepository;
import com.ssydorenko.hospital.domain.dto.VisitRequestDto;
import com.ssydorenko.hospital.utils.HospitalProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;


@Component
public class VisitRequestServiceValidator {

    @Autowired
    private DoctorServiceValidator doctorServiceValidator;

    @Autowired
    private VisitRequestRepository visitRequestRepository;

    @Autowired
    private HospitalProperties properties;


    public void validateVisitRequestIdExists(long visitRequestId) {

        if (!visitRequestRepository.existsById(visitRequestId)) {

            throw new IllegalArgumentException("Visit request with id " + visitRequestId + " does not exist");
        }
    }


    public void validateDoctorIdExists(long doctorId) {

        doctorServiceValidator.validateDoctorIdExists(doctorId);
    }


    public void validateAddRequest(VisitRequestDto visitRequestDto) {

        validateDoctorIdExists(visitRequestDto.getDoctorId());
        checkIfRequestedTimeOutOfBounds(visitRequestDto.getRequestedDatetime());
    }


    private void checkIfRequestedTimeOutOfBounds(LocalDateTime requestedTime) {

        LocalTime time = requestedTime.toLocalTime();
        LocalTime intervalEnd = properties.getWorkingDayEndTime()
                .minus(properties.getAverageAppointmentTimeMinutes(), ChronoUnit.MINUTES);

        if (time.isBefore(properties.getWorkingDayStartTime()) || time.isAfter(intervalEnd)) {

            throw new IllegalArgumentException("Wrong time has been set. Should be >= " +
                                               properties.getWorkingDayStartTime().toString() +
                                               " and <= " + intervalEnd.toString());
        }
    }
}
