package com.ssydorenko.hospital.domain.dto;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class VisitRequestDto {

    private String reason;

    private LocalDateTime desiredDatetime;

}
