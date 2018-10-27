package com.ssydorenko.hospital.utils.mapper;

import com.ssydorenko.hospital.domain.dto.PatientDto;
import com.ssydorenko.hospital.domain.entity.Patient;
import org.mapstruct.Mapper;


@Mapper
public interface PatientMapper {

    Patient toEntity(PatientDto dto);

    PatientDto toDto(Patient entity);

}
