package com.ssydorenko.hospital.utils.mapper;

import com.ssydorenko.hospital.domain.dto.PatientDto;
import com.ssydorenko.hospital.domain.entity.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel="spring")
public interface PatientMapper {

    Patient toEntity(PatientDto dto);

    @Mapping(source = "medicalCard.patientDescription", target = "medicalCardDto.patientDescription")
    @Mapping(source = "medicalCard.dateOfRegistration", target = "medicalCardDto.dateOfRegistration")
    PatientDto toDto(Patient entity);

}
