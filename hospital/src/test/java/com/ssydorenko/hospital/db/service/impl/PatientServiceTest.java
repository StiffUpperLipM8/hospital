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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientServiceTest {

    @MockBean
    private PatientRepository patientRepository;

    @MockBean
    private UserEntityRepository userEntityRepository;

    @Autowired
    private PatientMapper patientMapper;

    @Autowired
    private PatientService patientService;

    private static final long TEST_ID = 1L;


    @Test
    public void getAllPatientsShouldReturnListOfPatientDto() {

        List<Patient> patients = Collections.singletonList(generatePatient());
        when(patientRepository.findAll()).thenReturn(patients);

        List<PatientDto> result = patientService.getAllPatients();
        assertNotNull(result);
        assertEquals(generatePatientDto(), result.get(0));
    }


    @Test
    public void addPatientShouldAddPatientAndUserEntity() {

        PatientDto dto = generatePatientDto();
        dto.setPassword("1234");
        ArgumentCaptor<UserEntity> captor = ArgumentCaptor.forClass(UserEntity.class);

        patientService.addPatient(dto);

        verify(patientRepository).save(generatePatient());
        verify(userEntityRepository).save(captor.capture());

        UserEntity userEntity = captor.getValue();
        assertEquals(dto.getFullName(), userEntity.getFullName());
        assertEquals(UserRole.PATIENT, userEntity.getRole());
    }


    @Test
    public void getPatientByIdShouldReturnPatientDto() {

        when(patientRepository.getOne(TEST_ID)).thenReturn(generatePatient());
        PatientDto result = patientService.getPatientById(TEST_ID);
        assertEquals(generatePatientDto(), result);
    }


    @Test
    public void updatePatientDescriptionShouldUpdateDescription() {

        Patient patient = generatePatient();
        when(patientRepository.getOne(TEST_ID)).thenReturn(patient);
        patient.getMedicalCard().setPatientDescription("updated");

        patientService.updatePatientDescription(TEST_ID, "updated");

        verify(patientRepository).save(patient);
    }


    @Test
    public void deletePatientByIdShouldRemovePatientAndUserEntity() {

        when(patientRepository.getOne(TEST_ID)).thenReturn(generatePatient());

        patientService.deletePatientById(TEST_ID);

        verify(userEntityRepository).deleteById("test");
        verify(patientRepository).deleteById(TEST_ID);
    }


    private Patient generatePatient() {

        Patient patient = new Patient();

        patient.setFullName("test");
        patient.setId(1L);

        MedicalCard medicalCard = new MedicalCard();
        medicalCard.setId(0);
        medicalCard.setDateOfRegistration(LocalDate.now());
        patient.setMedicalCard(medicalCard);

        return patient;
    }


    private PatientDto generatePatientDto() {

        return patientMapper.toDto(generatePatient());
    }

}
