package com.ssydorenko.hospital.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DoctorDto {

    private Long id;

    private String fullName;

    private String doctorDescription;

    private String password;

}
