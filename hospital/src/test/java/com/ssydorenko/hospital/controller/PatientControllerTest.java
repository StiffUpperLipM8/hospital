package com.ssydorenko.hospital.controller;

import com.ssydorenko.hospital.db.service.api.PatientService;
import com.ssydorenko.hospital.domain.dto.PatientDto;
import com.ssydorenko.hospital.utils.validator.PatientServiceValidator;
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


public class PatientControllerTest extends AbstractControllerTest {

    @MockBean
    private PatientService patientService;

    @MockBean
    private PatientServiceValidator patientServiceValidator;

    private static final String PATIENTS_PATH = "/patients";


    @Test
    @WithMockUser(roles = "DOCTOR")
    public void getAllPatientsShouldInvokeServiceMethod() throws Exception {

        mockMvc.perform(get(PATIENTS_PATH))
                .andExpect(status().isOk());

        verify(patientService).getAllPatients();
    }


    @Test
    @WithMockUser(roles = "DOCTOR")
    public void getPatientByIdShouldInvokeServiceMethod() throws Exception {

        mockMvc.perform(get(PATIENTS_PATH + "/1"))
                .andExpect(status().isOk());

        verify(patientService).getPatientById(1);
    }


    @Test
    @WithMockUser(roles = "DOCTOR")
    public void addPatientsShouldInvokeServiceMethod() throws Exception {

        PatientDto patientDto = new PatientDto();

        mockMvc.perform(post(PATIENTS_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(marshalize(patientDto)))
                .andExpect(status().isOk());

        verify(patientService).addPatient(patientDto);
    }


    @Test
    @WithMockUser(roles = "DOCTOR")
    public void updatePatientDescriptionShouldInvokeServiceMethod() throws Exception {

        mockMvc.perform(put(PATIENTS_PATH + "/1")
                .content("test"))
                .andExpect(status().isOk());

        verify(patientService).updatePatientDescription(1, "test");
    }


    @Test
    @WithMockUser(roles = "DOCTOR")
    public void deletePatientByIdShouldInvokeServiceMethod() throws Exception {

        mockMvc.perform(delete(PATIENTS_PATH + "/1"))
                .andExpect(status().isOk());

        verify(patientService).deletePatientById(1);
    }

}
