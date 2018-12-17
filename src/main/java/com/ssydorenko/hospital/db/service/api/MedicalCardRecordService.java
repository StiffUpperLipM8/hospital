package com.ssydorenko.hospital.db.service.api;

import com.ssydorenko.hospital.domain.dto.MedicalCardRecordDto;

import java.util.List;

public interface MedicalCardRecordService {

    List<MedicalCardRecordDto> getAllMedicalCardRecords();

    MedicalCardRecordDto getMedicalCardRecordById(long recordId);

    void addMedicalCardRecord(MedicalCardRecordDto dto);

    void updateMedicalCardRecord(MedicalCardRecordDto dto);

    void deleteMedicalCardRecordById(long recordId);

}
