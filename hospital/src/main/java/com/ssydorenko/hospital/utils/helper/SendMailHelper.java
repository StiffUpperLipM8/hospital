package com.ssydorenko.hospital.utils.helper;

import com.ssydorenko.hospital.domain.dto.SendMailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class SendMailHelper {

    @Autowired
    private JavaMailSender emailSender;


    public void sendSimpleMessage(SendMailDto dto) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(dto.getTo());
        message.setSubject(dto.getSubject());
        message.setText(dto.getText());

        emailSender.send(message);
    }

}
