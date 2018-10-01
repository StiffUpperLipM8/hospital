package com.ssydorenko.hospital.controller;

import com.ssydorenko.hospital.domain.dto.DoctorDto;
import com.ssydorenko.hospital.db.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class DoctorController {

    @Autowired
    private DoctorService doctorService;


    @GetMapping("/doctors")
    public List<DoctorDto> getDoctors() {
        return doctorService.getDoctors();
    }


}
