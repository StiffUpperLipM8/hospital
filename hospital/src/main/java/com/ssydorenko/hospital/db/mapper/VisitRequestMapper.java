package com.ssydorenko.hospital.db.mapper;

import com.ssydorenko.hospital.domain.dto.VisitRequestDto;
import com.ssydorenko.hospital.domain.entity.VisitRequest;
import org.mapstruct.Mapper;


@Mapper(componentModel="spring")
public interface VisitRequestMapper {

    VisitRequest toEntity(VisitRequestDto dto);

    VisitRequestDto toDto(VisitRequest entity);

}
