package com.ssydorenko.hospital.db.service.impl;

import com.ssydorenko.hospital.db.repository.DoctorRepository;
import com.ssydorenko.hospital.db.repository.MedicalCardRecordRepository;
import com.ssydorenko.hospital.db.repository.MedicalCardRepository;
import com.ssydorenko.hospital.db.service.api.MedicalCardRecordService;
import com.ssydorenko.hospital.domain.dto.MedicalCardRecordDto;
import com.ssydorenko.hospital.domain.entity.MedicalCardRecord;
import com.ssydorenko.hospital.utils.mapper.MedicalCardRecordMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


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

    @Autowired
    private MedicalCardRecordService medicalCardRecordService;

    private static final long TEST_ID = 1L;


    @Test
    public void getAllRecordsShouldReturnListOfDto() {

        List<MedicalCardRecord> records = Collections.singletonList(generateRecord());
        when(medicalCardRecordRepository.findAll()).thenReturn(records);

        List<MedicalCardRecordDto> result = medicalCardRecordService.getAllMedicalCardRecords();
        assertEquals(generateRecordDto(), result.get(0));
    }


    @Test
    public void getRecordByIdShouldReturnRecordDto() {

        when(medicalCardRecordRepository.getOne(TEST_ID)).thenReturn(generateRecord());
        MedicalCardRecordDto result = medicalCardRecordService.getMedicalCardRecordById(TEST_ID);
        assertEquals(generateRecordDto(), result);
    }


    @Test
    public void addRecordShouldAddRecordWithCreationDateSet() {

        when(medicalCardRepository.existsById(TEST_ID)).thenReturn(true);
        when(doctorRepository.existsById(TEST_ID)).thenReturn(true);
        ArgumentCaptor<MedicalCardRecord> captor = ArgumentCaptor.forClass(MedicalCardRecord.class);

        medicalCardRecordService.addMedicalCardRecord(generateRecordDto());

        verify(medicalCardRecordRepository).save(captor.capture());
        assertNotNull(captor.getValue().getDateOfCreation());
    }


    @Test
    public void updateRecordShouldUpdateRecordInfo() {

        MedicalCardRecord record = generateRecord();
        MedicalCardRecordDto dto = generateRecordDto();
        when(medicalCardRecordRepository.getOne(TEST_ID)).thenReturn(record);
        when(doctorRepository.existsById(2L)).thenReturn(true);

        dto.setText("updated");
        dto.setDoctorId(2L);
        record.setText("updated");
        record.setDoctorId(2L);

        medicalCardRecordService.updateMedicalCardRecord(dto);

        verify(medicalCardRecordRepository).save(record);
    }


    @Test
    public void deleteRecordByIdShouldDeleteRecord() {

        medicalCardRecordService.deleteMedicalCardRecordById(TEST_ID);
        verify(medicalCardRecordRepository).deleteById(TEST_ID);
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
