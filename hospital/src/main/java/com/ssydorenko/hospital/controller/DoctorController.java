package com.ssydorenko.hospital.controller;

import com.ssydorenko.hospital.db.service.api.DoctorService;
import com.ssydorenko.hospital.domain.dto.DoctorDto;
import com.ssydorenko.hospital.domain.dto.VisitRequestDto;
import com.ssydorenko.hospital.utils.validator.DoctorServiceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private DoctorServiceValidator doctorValidator;


    @GetMapping
    public List<DoctorDto> getDoctors() {

        return doctorService.getDoctors();
    }


    @GetMapping("/{doctorId}")
    public DoctorDto getDoctorById(@PathVariable long doctorId) {

        doctorValidator.validateDoctorIdExists(doctorId);
        return doctorService.getDoctorById(doctorId);
    }


    @PostMapping
    public void addDoctor(@RequestBody DoctorDto doctorDto) {

        doctorValidator.validateDoctorNameNotExists(doctorDto.getFullName());
        doctorService.addDoctor(doctorDto);
    }


    @PutMapping
    public void updateDoctorDescription(@RequestBody DoctorDto doctorDto) {

        doctorValidator.validateDoctorIdExists(doctorDto.getId());
        doctorService.updateDoctorDescription(doctorDto);
    }


    @DeleteMapping("/{doctorId}")
    public void deleteDoctor(@PathVariable long doctorId) {

        doctorValidator.validateDoctorIdExists(doctorId);
        doctorService.deleteDoctorById(doctorId);
    }

}
