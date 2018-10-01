package com.ssydorenko.hospital.db.service;

import com.ssydorenko.hospital.domain.dto.DoctorDto;
import com.ssydorenko.hospital.db.repository.DoctorRepository;
import com.ssydorenko.hospital.db.mapper.DoctorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;


    public List<DoctorDto> getDoctors() {
        return doctorRepository.findAll().stream()
                .map(DoctorMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }


    public DoctorDto getDoctorById(long doctorId) {
        return DoctorMapper.INSTANCE.toDto(doctorRepository.getOne(doctorId));
    }

}
