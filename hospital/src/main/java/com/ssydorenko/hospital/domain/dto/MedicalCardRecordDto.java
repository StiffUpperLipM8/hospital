package com.ssydorenko.hospital.domain.dto;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class MedicalCardRecordDto {

    private LocalDateTime dateOfCreation;

    private String text;

    private String doctorFullName;

}
