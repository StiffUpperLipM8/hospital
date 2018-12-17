package com.ssydorenko.hospital.domain.dto;

import com.ssydorenko.hospital.domain.enums.RequestStatus;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class VisitRequestDto {

    private Long id;

    private String reason;

    private LocalDateTime requestedDatetime;

    private RequestStatus status;

    private LocalDateTime lastStatusChangeDateTime;

    private String statusDescription;

    private Long doctorId;

}
