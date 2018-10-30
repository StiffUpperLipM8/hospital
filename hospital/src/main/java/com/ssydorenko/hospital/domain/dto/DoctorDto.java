package com.ssydorenko.hospital.domain.dto;

import lombok.Data;


@Data
public class DoctorDto {

    private long id;

    private String fullName;

    private String doctorDescription;

    private String password;

}
