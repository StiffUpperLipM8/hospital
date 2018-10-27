package com.ssydorenko.hospital.controller;

import com.ssydorenko.hospital.db.service.api.PatientService;
import com.ssydorenko.hospital.domain.dto.PatientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;


    @GetMapping("{patientId}")
    public PatientDto getPatientById(@PathVariable long patientId) {

        return patientService.getPatientById(patientId);
    }


    @PostMapping
    public void addPatient(@RequestBody PatientDto patientDto) {

        patientService.addPatient(patientDto);
    }


    @DeleteMapping("{patientId")
    public void deletePatientById(@PathVariable long patientId) {

        patientService.deletePatientById(patientId);
    }

}
