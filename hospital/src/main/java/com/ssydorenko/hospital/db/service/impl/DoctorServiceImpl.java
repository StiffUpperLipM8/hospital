package com.ssydorenko.hospital.db.service.impl;

import com.ssydorenko.hospital.db.repository.DoctorRepository;
import com.ssydorenko.hospital.db.service.api.DoctorService;
import com.ssydorenko.hospital.domain.dto.DoctorDto;
import com.ssydorenko.hospital.domain.dto.VisitRequestDto;
import com.ssydorenko.hospital.utils.mapper.DoctorMapper;
import com.ssydorenko.hospital.utils.mapper.VisitRequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private DoctorMapper doctorMapper;

    @Autowired
    private VisitRequestMapper visitRequestMapper;


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

        return doctorRepository.getOne(doctorId).getSchedule()
                .stream()
                .map(visitRequestMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public void addDoctor(DoctorDto doctorDto) {

        doctorRepository.save(doctorMapper.toEntity(doctorDto));
    }


    @Override
    @Transactional
    public void deleteDoctorById(long doctorId) {

        doctorRepository.deleteById(doctorId);
    }

}
