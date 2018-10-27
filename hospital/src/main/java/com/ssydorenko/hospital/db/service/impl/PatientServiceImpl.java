package com.ssydorenko.hospital.db.service.impl;

import com.ssydorenko.hospital.db.repository.PatientRepository;
import com.ssydorenko.hospital.db.service.api.PatientService;
import com.ssydorenko.hospital.domain.dto.PatientDto;
import com.ssydorenko.hospital.domain.entity.MedicalCard;
import com.ssydorenko.hospital.domain.entity.Patient;
import com.ssydorenko.hospital.utils.mapper.PatientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientMapper patientMapper;


    @Override
    public void addPatient(PatientDto patientDto) {

        Patient patient = patientMapper.toEntity(patientDto);

        MedicalCard medicalCard = new MedicalCard();
        medicalCard.setPatientDescription(patientDto.getPatientDescription());
        medicalCard.setDateOfRegistration(LocalDate.now());

        patient.setMedicalCard(medicalCard);
        patientRepository.save(patient);
    }


    @Override
    public void deletePatientById(long patientId) {

        patientRepository.deleteById(patientId);
    }


    @Override
    public PatientDto getPatientById(long patientId) {

        return patientMapper.toDto(patientRepository.getOne(patientId));
    }

}
