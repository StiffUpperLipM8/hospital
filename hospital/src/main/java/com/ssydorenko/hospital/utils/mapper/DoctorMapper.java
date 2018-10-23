package com.ssydorenko.hospital.utils.mapper;

import com.ssydorenko.hospital.domain.dto.DoctorDto;
import com.ssydorenko.hospital.domain.entity.Doctor;
import org.mapstruct.Mapper;


@Mapper(componentModel="spring")
public interface DoctorMapper {

    Doctor toEntity(DoctorDto dto);

    DoctorDto toDto(Doctor entity);

}
