package com.ssydorenko.hospital.controller;

import com.ssydorenko.hospital.db.service.api.DoctorService;
import com.ssydorenko.hospital.domain.dto.DoctorDto;
import com.ssydorenko.hospital.domain.dto.VisitRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;


    @GetMapping
    public List<DoctorDto> getDoctors() {

        return doctorService.getDoctors();
    }


    @GetMapping("/{doctorId}")
    public DoctorDto getDoctorById(@PathVariable long doctorId) {

        return doctorService.getDoctorById(doctorId);
    }


    @GetMapping("/{doctorId}/schedule")
    public List<VisitRequestDto> getDoctorSchedule(@PathVariable long doctorId) {

        return doctorService.getDoctorScheduleByDoctorId(doctorId);
    }


    @PostMapping
    public void addDoctor(@RequestBody DoctorDto doctorDto) {

        doctorService.addDoctor(doctorDto);
    }


    @PutMapping
    public void updateDoctorDescription(@RequestBody DoctorDto doctorDto) {

        doctorService.updateDoctorDescription(doctorDto);
    }


    @DeleteMapping("/{doctorId}")
    public void deleteDoctor(@PathVariable long doctorId) {

        doctorService.deleteDoctorById(doctorId);
    }

}
