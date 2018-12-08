package com.ssydorenko.hospital.db.service.impl;

import com.ssydorenko.hospital.db.repository.DoctorRepository;
import com.ssydorenko.hospital.db.repository.UserEntityRepository;
import com.ssydorenko.hospital.db.repository.VisitRequestRepository;
import com.ssydorenko.hospital.db.service.api.DoctorService;
import com.ssydorenko.hospital.domain.dto.DoctorDto;
import com.ssydorenko.hospital.domain.dto.VisitRequestDto;
import com.ssydorenko.hospital.domain.entity.Doctor;
import com.ssydorenko.hospital.domain.entity.UserEntity;
import com.ssydorenko.hospital.domain.enums.UserRole;
import com.ssydorenko.hospital.utils.mapper.DoctorMapper;
import com.ssydorenko.hospital.utils.mapper.VisitRequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private VisitRequestRepository visitRequestRepository;

    @Autowired
    private DoctorMapper doctorMapper;

    @Autowired
    private VisitRequestMapper visitRequestMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public List<DoctorDto> getDoctors() {

        return doctorRepository.findAll()
                .stream()
                .map(doctorMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public DoctorDto getDoctorById(long doctorId) {

        return doctorMapper.toDto(doctorRepository.getOne(doctorId));
    }


    @Override
    public List<VisitRequestDto> getDoctorScheduleByDoctorId(long doctorId) {

        return visitRequestRepository.getApprovedRequestsByDoctorId(doctorId)
                .stream()
                .map(visitRequestMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public void addDoctor(DoctorDto doctorDto) {

        doctorMapper.toEntity(doctorDto);
        doctorRepository.save(doctorMapper.toEntity(doctorDto));

        String password = passwordEncoder.encode(doctorDto.getPassword());
        UserEntity userEntity = new UserEntity(doctorDto.getFullName(), password, UserRole.DOCTOR);
        userEntityRepository.save(userEntity);
    }


    @Override
    @Transactional
    public void updateDoctorDescription(DoctorDto doctorDto) {

        Doctor doctor = doctorRepository.getOne(doctorDto.getId());
        doctor.setDoctorDescription(doctorDto.getDoctorDescription());
        doctorRepository.save(doctor);
    }


    @Override
    @Transactional
    public void deleteDoctorById(long doctorId) {

        String doctorFullName = doctorRepository.getOne(doctorId).getFullName();
        userEntityRepository.deleteById(doctorFullName);
        doctorRepository.deleteById(doctorId);
    }

}
