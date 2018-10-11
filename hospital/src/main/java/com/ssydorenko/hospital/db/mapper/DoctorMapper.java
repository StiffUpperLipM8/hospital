package com.ssydorenko.hospital.db.mapper;

import com.ssydorenko.hospital.domain.dto.DoctorDto;
import com.ssydorenko.hospital.domain.entity.Doctor;
import org.mapstruct.Mapper;


@Mapper(componentModel="spring")
public interface DoctorMapper {

    Doctor toEntity(DoctorDto doctorDto);

    DoctorDto toDto(Doctor doctor);

}
