package com.ssydorenko.hospital.domain.dto;

import com.ssydorenko.hospital.domain.enums.VisitRequestStatus;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class VisitRequestDto {

    private String reason;

    private LocalDateTime desiredDatetime;

    private VisitRequestStatus status;

}
