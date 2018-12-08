package com.ssydorenko.hospital.utils.validator;


import com.ssydorenko.hospital.db.repository.MedicalCardRecordRepository;
import com.ssydorenko.hospital.db.repository.MedicalCardRepository;
import com.ssydorenko.hospital.domain.dto.MedicalCardRecordDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicalCardRecordServiceValidator {

    @Autowired
    private MedicalCardRepository medicalCardRepository;

    @Autowired
    private MedicalCardRecordRepository medicalCardRecordRepository;

    @Autowired
    private DoctorServiceValidator doctorServiceValidator;


    public void validateMedicalCardRecordIdExists(long medicalCardRecordId) {

        if(!medicalCardRecordRepository.existsById(medicalCardRecordId)) {

            throw new IllegalArgumentException("Medical card record with id " + medicalCardRecordId + " does not exist");
        }
    }


    public void validateAddRecord(MedicalCardRecordDto dto) {

        if(!medicalCardRepository.existsById(dto.getMedicalCardId())) {

            throw new IllegalArgumentException("Medical card with id: " + dto.getMedicalCardId() + " does not exist");
        }
        doctorServiceValidator.validateDoctorIdExists(dto.getDoctorId());
    }


    public void validateUpdateRecord(MedicalCardRecordDto dto) {

        if(dto.getDoctorId() != null) {

           doctorServiceValidator.validateDoctorIdExists(dto.getDoctorId());
        }
    }

}
