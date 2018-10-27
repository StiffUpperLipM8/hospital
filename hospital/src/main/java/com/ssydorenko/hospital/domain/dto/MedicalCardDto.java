package com.ssydorenko.hospital.domain.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class MedicalCardDto {

    private String patientDescription;

    private LocalDate dateOfRegistration;

    private List<MedicalCardRecordDto> records;

}
