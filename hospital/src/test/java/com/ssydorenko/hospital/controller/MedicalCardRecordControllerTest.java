package com.ssydorenko.hospital.controller;

import com.ssydorenko.hospital.db.service.api.MedicalCardRecordService;
import com.ssydorenko.hospital.domain.dto.MedicalCardRecordDto;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class MedicalCardRecordControllerTest extends AbstractControllerTest {

    @MockBean
    private MedicalCardRecordService medicalCardRecordService;

    private static final String RECORDS_PATH = "/records";


    @Test
    @WithMockUser(roles = "DOCTOR")
    public void getAllRecordsShouldInvokeServiceMethod() throws Exception {

        mockMvc.perform(get(RECORDS_PATH))
                .andExpect(status().isOk());

        verify(medicalCardRecordService).getAllMedicalCardRecords();
    }


    @Test
    @WithMockUser(roles = "DOCTOR")
    public void getRecordByIdShouldInvokeServiceMethod() throws Exception {

        mockMvc.perform(get(RECORDS_PATH + "/1"))
                .andExpect(status().isOk());

        verify(medicalCardRecordService).getMedicalCardRecordById(1);
    }


    @Test
    @WithMockUser(roles = "DOCTOR")
    public void addRecordShouldInvokeServiceMethod() throws Exception {

        MedicalCardRecordDto dto = new MedicalCardRecordDto();

        mockMvc.perform(post(RECORDS_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(marshalize(dto)))
                .andExpect(status().isOk());

        verify(medicalCardRecordService).addMedicalCardRecord(dto);
    }


    @Test
    @WithMockUser(roles = "DOCTOR")
    public void updateRecordShouldInvokeServiceMethod() throws Exception {

        MedicalCardRecordDto dto = new MedicalCardRecordDto();

        mockMvc.perform(put(RECORDS_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(marshalize(dto)))
                .andExpect(status().isOk());

        verify(medicalCardRecordService).updateMedicalCardRecord(dto);
    }


    @Test
    @WithMockUser(roles = "DOCTOR")
    public void deleteRecordByIdShouldInvokeServiceMethod() throws Exception {

        mockMvc.perform(delete(RECORDS_PATH + "/1"))
                .andExpect(status().isOk());

        verify(medicalCardRecordService).deleteMedicalCardRecordById(1);
    }

}
