package com.ssydorenko.hospital.db.service.api;

import com.ssydorenko.hospital.domain.dto.DoctorDto;
import com.ssydorenko.hospital.domain.dto.VisitRequestDto;

import java.time.LocalDate;
import java.util.List;


public interface DoctorService {

    List<DoctorDto> getDoctors();

    DoctorDto getDoctorById(long doctorId);

    void addDoctor(DoctorDto doctorDto);

    void updateDoctorDescription(DoctorDto doctorDto);

    void deleteDoctorById(long doctorId);

}
