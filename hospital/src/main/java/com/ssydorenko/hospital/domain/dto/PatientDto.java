package com.ssydorenko.hospital.domain.dto;

import lombok.Data;


@Data
public class PatientDto {

    private long id;

    private String firstName;

    private String lastName;

    private MedicalCardDto medicalCardDto;

}
