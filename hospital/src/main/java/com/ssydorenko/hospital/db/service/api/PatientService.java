package com.ssydorenko.hospital.db.service.api;

import com.ssydorenko.hospital.domain.dto.PatientDto;


public interface PatientService {

    void addPatient(PatientDto patientDto);

    void deletePatientById(long patientId);

    PatientDto getPatientById(long patientId);

}
