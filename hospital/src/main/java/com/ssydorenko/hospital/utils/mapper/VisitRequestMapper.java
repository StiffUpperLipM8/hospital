package com.ssydorenko.hospital.utils.mapper;

import com.ssydorenko.hospital.domain.dto.VisitRequestDto;
import com.ssydorenko.hospital.domain.entity.VisitRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel="spring")
public interface VisitRequestMapper {

    @Mapping(source = "status", target = "visitRequestStatus.status")
    @Mapping(source = "lastStatusChangeDateTime", target = "visitRequestStatus.lastStatusChangeDateTime")
    @Mapping(source = "statusDescription", target = "visitRequestStatus.statusDescription")
    VisitRequest toEntity(VisitRequestDto dto);

    @Mapping(source = "visitRequestStatus.status", target = "status")
    @Mapping(source = "visitRequestStatus.lastStatusChangeDateTime", target = "lastStatusChangeDateTime")
    @Mapping(source = "visitRequestStatus.statusDescription", target = "statusDescription")
    VisitRequestDto toDto(VisitRequest entity);

}
