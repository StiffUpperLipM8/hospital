package com.ssydorenko.hospital.domain.dto;

import lombok.Data;


@Data
public class DoctorDto {

    private long id;

    private String firstName;

    private String lastName;

    private String doctorDescription;

}
