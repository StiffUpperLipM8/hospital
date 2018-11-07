package com.ssydorenko.hospital.db.service.impl;

import com.ssydorenko.hospital.db.repository.DoctorRepository;
import com.ssydorenko.hospital.db.repository.MedicalCardRecordRepository;
import com.ssydorenko.hospital.db.repository.MedicalCardRepository;
import com.ssydorenko.hospital.db.service.api.MedicalCardRecordService;
import com.ssydorenko.hospital.domain.dto.MedicalCardRecordDto;
import com.ssydorenko.hospital.domain.entity.MedicalCardRecord;
import com.ssydorenko.hospital.utils.mapper.MedicalCardRecordMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class MedicalCardRecordServiceImpl implements MedicalCardRecordService {

    @Autowired
    private MedicalCardRecordRepository medicalCardRecordRepository;

    @Autowired
    private MedicalCardRepository medicalCardRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private MedicalCardRecordMapper medicalCardRecordMapper;


    @Override
    public List<MedicalCardRecordDto> getAllMedicalCardRecords() {

        return medicalCardRecordRepository.findAll().stream()
                .map(medicalCardRecordMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public MedicalCardRecordDto getMedicalCardRecordById(long recordId) {

        return medicalCardRecordMapper.toDto(medicalCardRecordRepository.getOne(recordId));
    }


    @Override
    public void addMedicalCardRecord(MedicalCardRecordDto dto) {

        if(!medicalCardRepository.existsById(dto.getMedicalCardId())) {

            throw new IllegalArgumentException("Medical card with id: " + dto.getMedicalCardId() + " does not exist");
        }

        if(!doctorRepository.existsById(dto.getDoctorId())) {

            throw new IllegalArgumentException("Doctor with id: " + dto.getDoctorId() + " does not exist");
        }

        MedicalCardRecord record = medicalCardRecordMapper.toEntity(dto);
        record.setDateOfCreation(LocalDateTime.now());
        medicalCardRecordRepository.save(record);
    }


    @Override
    public void updateMedicalCardRecord(MedicalCardRecordDto dto) {

        MedicalCardRecord record = medicalCardRecordRepository.getOne(dto.getId());

        if(StringUtils.isNotBlank(dto.getText())) {

            record.setText(dto.getText());
        }
        if(dto.getDoctorId() != null) {

            record.setDoctorId(dto.getDoctorId());
        }
        record.setDateOfCreation(LocalDateTime.now());

        medicalCardRecordRepository.save(record);
    }


    @Override
    public void deleteMedicalCardRecordById(long recordId) {

        medicalCardRecordRepository.deleteById(recordId);
    }

}
