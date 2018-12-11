package com.ssydorenko.hospital.utils.validator;

import com.ssydorenko.hospital.db.repository.VisitRequestRepository;
import com.ssydorenko.hospital.domain.dto.VisitRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class VisitRequestServiceValidator {

    @Autowired
    private DoctorServiceValidator doctorServiceValidator;

    @Autowired
    private VisitRequestRepository visitRequestRepository;


    public void validateVisitRequestIdExists(long visitRequestId) {

        if (!visitRequestRepository.existsById(visitRequestId)) {

            throw new IllegalArgumentException("Visit request with id " + visitRequestId + " does not exist");
        }
    }


    public void validateDoctorIdExists(long doctorId) {

        doctorServiceValidator.validateDoctorIdExists(doctorId);
    }

}
