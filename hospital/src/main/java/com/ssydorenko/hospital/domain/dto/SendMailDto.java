package com.ssydorenko.hospital.domain.dto;

import lombok.Data;

@Data
public class SendMailDto {

    private String text;

    private String to;

    private String subject;

}
