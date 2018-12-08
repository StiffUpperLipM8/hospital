package com.ssydorenko.hospital.controller;

import com.ssydorenko.hospital.db.service.api.MedicalCardRecordService;
import com.ssydorenko.hospital.domain.dto.MedicalCardRecordDto;
import com.ssydorenko.hospital.utils.validator.MedicalCardRecordServiceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/records")
public class MedicalCardRecordController {

    @Autowired
    private MedicalCardRecordService medicalCardRecordService;

    @Autowired
    private MedicalCardRecordServiceValidator medicalCardRecordServiceValidator;

    @GetMapping
    public List<MedicalCardRecordDto> getAllRecords() {

        return medicalCardRecordService.getAllMedicalCardRecords();
    }


    @GetMapping("{recordId}")
    public MedicalCardRecordDto getMedicalCardRecordById(@PathVariable long recordId) {

        medicalCardRecordServiceValidator.validateMedicalCardRecordIdExists(recordId);
        return medicalCardRecordService.getMedicalCardRecordById(recordId);
    }


    @PostMapping
    public void addMedicalCardRecord(@RequestBody MedicalCardRecordDto dto) {

        medicalCardRecordServiceValidator.validateAddRecord(dto);
        medicalCardRecordService.addMedicalCardRecord(dto);
    }


    @PutMapping
    public void updateMedicalCardRecord(@RequestBody MedicalCardRecordDto dto) {

        medicalCardRecordServiceValidator.validateUpdateRecord(dto);
        medicalCardRecordService.updateMedicalCardRecord(dto);
    }


    @DeleteMapping("/{recordId}")
    public void deleteMedicalCardRecordById(@PathVariable long recordId) {

        medicalCardRecordServiceValidator.validateMedicalCardRecordIdExists(recordId);
        medicalCardRecordService.deleteMedicalCardRecordById(recordId);
    }

}
