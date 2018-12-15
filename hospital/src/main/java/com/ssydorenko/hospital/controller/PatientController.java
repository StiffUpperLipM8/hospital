package com.ssydorenko.hospital.controller;

import com.ssydorenko.hospital.db.service.api.PatientService;
import com.ssydorenko.hospital.domain.dto.PatientDto;
import com.ssydorenko.hospital.utils.validator.PatientServiceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientServiceValidator patientServiceValidator;


    @GetMapping
    public List<PatientDto> getAllPatients() {

        return patientService.getAllPatients();
    }


    @GetMapping("{patientId}")
    public PatientDto getPatientById(@PathVariable long patientId) {

        patientServiceValidator.validatePatientIdExists(patientId);
        return patientService.getPatientById(patientId);
    }


    @PostMapping
    public void addPatient(@RequestBody PatientDto patientDto) {

        patientServiceValidator.validatePatientFullNameNotExists(patientDto.getFullName());
        patientService.addPatient(patientDto);
    }


    @PutMapping("{patientId}")
    public void updatePatientDescription(@PathVariable long patientId, @RequestBody String description) {

        patientServiceValidator.validatePatientIdExists(patientId);
        patientService.updatePatientDescription(patientId, description);
    }


    @DeleteMapping("{patientId}")
    public void deletePatientById(@PathVariable long patientId) {

        patientServiceValidator.validatePatientIdExists(patientId);
        patientService.deletePatientById(patientId);
    }

}
