package com.ssydorenko.hospital.db.service.impl;

import com.ssydorenko.hospital.db.repository.PatientRepository;
import com.ssydorenko.hospital.db.repository.UserEntityRepository;
import com.ssydorenko.hospital.db.service.api.PatientService;
import com.ssydorenko.hospital.domain.dto.PatientDto;
import com.ssydorenko.hospital.domain.entity.MedicalCard;
import com.ssydorenko.hospital.domain.entity.Patient;
import com.ssydorenko.hospital.domain.entity.UserEntity;
import com.ssydorenko.hospital.domain.enums.UserRole;
import com.ssydorenko.hospital.utils.mapper.PatientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientMapper patientMapper;

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public List<PatientDto> getAllPatients() {

        return patientRepository.findAll().stream()
                .map(patientMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public void addPatient(PatientDto patientDto) {

        Patient patient = patientMapper.toEntity(patientDto);

        MedicalCard medicalCard = new MedicalCard();
        medicalCard.setDateOfRegistration(LocalDate.now());

        patient.setMedicalCard(medicalCard);
        patientRepository.save(patient);

        String password = passwordEncoder.encode(patientDto.getPassword());
        UserEntity userEntity = new UserEntity(patientDto.getFullName(), password, UserRole.PATIENT);
        userEntityRepository.save(userEntity);
    }


    @Override
    public PatientDto getPatientById(long patientId) {

        return patientMapper.toDto(patientRepository.getOne(patientId));
    }


    @Override
    @Transactional
    public void updatePatientDescription(long patientId, String description) {

        Patient patient = patientRepository.getOne(patientId);
        patient.getMedicalCard().setPatientDescription(description);
        patientRepository.save(patient);
    }


    @Override
    public void deletePatientById(long patientId) {

        String patientFullName = patientRepository.getOne(patientId).getFullName();
        userEntityRepository.deleteById(patientFullName);
        patientRepository.deleteById(patientId);
    }

}
