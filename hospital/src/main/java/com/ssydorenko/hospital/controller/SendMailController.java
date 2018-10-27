package com.ssydorenko.hospital.controller;


import com.ssydorenko.hospital.domain.dto.SendMailDto;
import com.ssydorenko.hospital.utils.helper.SendMailHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
public class SendMailController {

    @Autowired
    private SendMailHelper sendMailHelper;


    @PostMapping
    public void sendSimpleMail(@RequestBody SendMailDto sendMailDto) {

        sendMailHelper.sendSimpleMessage(sendMailDto);
    }

}
