package com.ssydorenko.hospital.domain.dto;

import lombok.Data;


@Data
public class PatientDto {

    private long id;

    private String fullName;

    private MedicalCardDto medicalCardDto;

    private String password;

}
