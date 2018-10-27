package com.ssydorenko.hospital.controller;

import com.ssydorenko.hospital.db.service.api.DoctorService;
import com.ssydorenko.hospital.domain.dto.DoctorDto;
import com.ssydorenko.hospital.domain.dto.VisitRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    @DeleteMapping("/{doctorId}")
    public void deleteDoctor(@PathVariable long doctorId) {

        doctorService.deleteDoctorById(doctorId);
    }

}
