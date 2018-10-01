package com.ssydorenko.hospital.db.mapper;

import com.ssydorenko.hospital.domain.dto.DoctorDto;
import com.ssydorenko.hospital.domain.entity.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface DoctorMapper {

    DoctorMapper INSTANCE = Mappers.getMapper(DoctorMapper.class);

    Doctor toEntity(DoctorDto doctorDto);

    DoctorDto toDto(Doctor doctor);

}
