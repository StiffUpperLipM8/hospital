package com.ssydorenko.hospital.db.service.api;

import com.ssydorenko.hospital.domain.dto.PatientDto;

import java.util.List;


public interface PatientService {

    List<PatientDto> getAllPatients();

    void addPatient(PatientDto patientDto);

    PatientDto getPatientById(long patientId);

    void updatePatientDescription(long patientId, String description);

    void deletePatientById(long patientId);

}
