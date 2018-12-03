package com.ssydorenko.hospital.db.service.impl;

import com.ssydorenko.hospital.db.repository.DoctorRepository;
import com.ssydorenko.hospital.db.repository.MedicalCardRecordRepository;
import com.ssydorenko.hospital.db.repository.MedicalCardRepository;
import com.ssydorenko.hospital.domain.dto.MedicalCardRecordDto;
import com.ssydorenko.hospital.domain.entity.MedicalCardRecord;
import com.ssydorenko.hospital.utils.mapper.MedicalCardRecordMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MedicalCardRecordServiceTest {

    @MockBean
    private MedicalCardRecordRepository medicalCardRecordRepository;

    @MockBean
    private MedicalCardRepository medicalCardRepository;

    @MockBean
    private DoctorRepository doctorRepository;

    @Autowired
    private MedicalCardRecordMapper medicalCardRecordMapper;

    private static final long TEST_ID = 1L;


    @Test
    public void getAllRecordsShouldReturnListOfDto() {

        List<MedicalCardRecord> records = Collections.singletonList(generateRecord());
    }


    private MedicalCardRecord generateRecord() {

        MedicalCardRecord medicalCardRecord = new MedicalCardRecord();
        medicalCardRecord.setId(TEST_ID);
        medicalCardRecord.setDoctorId(TEST_ID);
        medicalCardRecord.setMedicalCardId(TEST_ID);
        medicalCardRecord.setText("test");
        return medicalCardRecord;
    }

    private MedicalCardRecordDto generateRecordDto() {

        return medicalCardRecordMapper.toDto(generateRecord());
    }

}
