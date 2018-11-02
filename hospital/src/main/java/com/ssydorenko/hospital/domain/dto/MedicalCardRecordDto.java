package com.ssydorenko.hospital.domain.dto;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class MedicalCardRecordDto {

    private Long id;

    private LocalDateTime dateOfCreation;

    private String text;

    private Long doctorId;

    private Long medicalCardId;

}
