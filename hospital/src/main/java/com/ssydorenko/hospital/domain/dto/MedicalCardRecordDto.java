package com.ssydorenko.hospital.domain.dto;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class MedicalCardRecordDto {

    private long id;

    private LocalDateTime dateOfCreation;

    private String text;

    private long doctorId;

    private long medicalCardId;

}
