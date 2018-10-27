package com.ssydorenko.hospital.utils.mapper;

import com.ssydorenko.hospital.domain.dto.MedicalCardRecordDto;
import com.ssydorenko.hospital.domain.entity.MedicalCardRecord;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface MedicalCardRecordMapper {

    MedicalCardRecord toEntity(MedicalCardRecordDto dto);

    MedicalCardRecordDto toDto(MedicalCardRecord entity);

}
