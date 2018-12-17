package com.ssydorenko.hospital.utils.validator;

import com.ssydorenko.hospital.db.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class PatientServiceValidator {

    @Autowired
    private PatientRepository patientRepository;


    public void validatePatientFullNameNotExists(String patientFullName) {

        if (patientRepository.findByFullName(patientFullName) != null) {

            throw new IllegalArgumentException("Patient with full name " + patientFullName + " already exists");
        }
    }


    public void validatePatientIdExists(long patientId) {

        if (!patientRepository.existsById(patientId)) {

            throw new IllegalArgumentException(("Patient with id " + patientId + " does not exist"));
        }
    }

}
