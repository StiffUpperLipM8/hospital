package com.ssydorenko.hospital.db.service;

import com.ssydorenko.hospital.db.mapper.DoctorMapper;
import com.ssydorenko.hospital.db.mapper.VisitRequestMapper;
import com.ssydorenko.hospital.db.repository.DoctorRepository;
import com.ssydorenko.hospital.domain.dto.DoctorDto;
import com.ssydorenko.hospital.domain.dto.VisitRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private DoctorMapper doctorMapper;

    @Autowired
    private VisitRequestMapper visitRequestMapper;


    public List<DoctorDto> getDoctors() {
        return doctorRepository.findAll()
                .stream()
                .map(doctorMapper::toDto)
                .collect(Collectors.toList());
    }

    public DoctorDto getDoctorById(long doctorId) {
        return doctorMapper.toDto(doctorRepository.getOne(doctorId));
    }

    public List<VisitRequestDto> getDoctorScheduleByDoctorId(long doctorId) {
        return doctorRepository.getOne(doctorId).getSchedule()
                .stream()
                .map(visitRequestMapper::toDto)
                .collect(Collectors.toList());
    }

    public void addDoctor(DoctorDto doctorDto) {
        doctorRepository.save(doctorMapper.toEntity(doctorDto));
    }




}
