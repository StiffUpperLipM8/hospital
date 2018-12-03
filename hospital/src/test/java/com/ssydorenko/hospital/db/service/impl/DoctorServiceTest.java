package com.ssydorenko.hospital.db.service.impl;

import com.ssydorenko.hospital.db.repository.DoctorRepository;
import com.ssydorenko.hospital.db.repository.UserEntityRepository;
import com.ssydorenko.hospital.db.repository.VisitRequestRepository;
import com.ssydorenko.hospital.db.service.api.DoctorService;
import com.ssydorenko.hospital.domain.dto.DoctorDto;
import com.ssydorenko.hospital.domain.dto.VisitRequestDto;
import com.ssydorenko.hospital.domain.entity.Doctor;
import com.ssydorenko.hospital.domain.entity.UserEntity;
import com.ssydorenko.hospital.domain.entity.VisitRequest;
import com.ssydorenko.hospital.domain.enums.UserRole;
import com.ssydorenko.hospital.utils.mapper.DoctorMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DoctorServiceTest {

    @MockBean
    private DoctorRepository doctorRepository;

    @MockBean
    private UserEntityRepository userEntityRepository;

    @MockBean
    private VisitRequestRepository visitRequestRepository;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private DoctorMapper doctorMapper;

    private static final long TEST_ID = 1L;


    @Test
    public void getDoctorsShouldReturnListOfDoctorDto() {

        List<Doctor> doctors = Collections.singletonList(generateDoctor());
        when(doctorRepository.findAll()).thenReturn(doctors);

        List<DoctorDto> result = doctorService.getDoctors();

        assertNotNull(result);
        assertEquals(generateDoctorDto(), result.get(0));
    }


    @Test
    public void getDoctorByIdShouldReturnDoctorDto() {

        when(doctorRepository.getOne(TEST_ID)).thenReturn(generateDoctor());

        DoctorDto result = doctorService.getDoctorById(TEST_ID);

        assertEquals(generateDoctorDto(), result);
    }


    @Test
    public void getDoctorScheduleByIdShouldReturnListOfVisitRequestDto() {

        VisitRequestDto visitRequestDto = new VisitRequestDto();
        visitRequestDto.setId(0L);
        visitRequestDto.setDoctorId(0L);
        List<VisitRequest> visitRequests = Collections.singletonList(new VisitRequest());

        when(visitRequestRepository.getApprovedRequestsByDoctorId(TEST_ID)).thenReturn(visitRequests);

        List<VisitRequestDto> result = doctorService.getDoctorScheduleByDoctorId(TEST_ID);

        assertNotNull(result);
        assertEquals(visitRequestDto, result.get(0));
    }


    @Test(expected = IllegalArgumentException.class)
    public void addExistingDoctorShouldThrowException() {

        when(doctorRepository.findByFullName("test")).thenReturn(generateDoctor());
        doctorService.addDoctor(generateDoctorDto());
    }


    @Test
    public void addDoctorShouldAddDoctorAndUserEntity() {

        DoctorDto dto = generateDoctorDto();
        dto.setPassword("1234");
        ArgumentCaptor<UserEntity> captor = ArgumentCaptor.forClass(UserEntity.class);

        doctorService.addDoctor(dto);

        verify(doctorRepository).save(generateDoctor());
        verify(userEntityRepository).save(captor.capture());

        UserEntity userEntity = captor.getValue();
        assertEquals(dto.getFullName(), userEntity.getFullName());
        assertEquals(UserRole.DOCTOR, userEntity.getRole());
    }


    @Test
    public void updateDoctorDescriptionShouldUpdateDescription() {

        Doctor doctor = generateDoctor();
        DoctorDto doctorDto = generateDoctorDto();

        when(doctorRepository.getOne(TEST_ID)).thenReturn(doctor);

        doctor.setDoctorDescription("updated");
        doctorDto.setDoctorDescription("updated");

        doctorService.updateDoctorDescription(doctorDto);

        verify(doctorRepository).save(doctor);
    }


    @Test
    public void deleteDoctorShouldDeleteDoctorAndUserEntity() {

        when(doctorRepository.getOne(TEST_ID)).thenReturn(generateDoctor());

        doctorService.deleteDoctorById(TEST_ID);

        verify(userEntityRepository).deleteById("test");
        verify(doctorRepository).deleteById(TEST_ID);
    }


    private Doctor generateDoctor() {

        Doctor doctor = new Doctor();

        doctor.setDoctorDescription("test");
        doctor.setFullName("test");
        doctor.setId(1);

        return doctor;
    }


    private DoctorDto generateDoctorDto() {

        return doctorMapper.toDto(generateDoctor());
    }

}
