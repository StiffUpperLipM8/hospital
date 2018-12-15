package com.ssydorenko.hospital.controller;

import com.ssydorenko.hospital.db.service.api.DoctorService;
import com.ssydorenko.hospital.domain.dto.DoctorDto;
import com.ssydorenko.hospital.utils.validator.DoctorServiceValidator;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class DoctorControllerTest extends AbstractControllerTest {

    @MockBean
    private DoctorService doctorService;

    @MockBean
    private DoctorServiceValidator doctorServiceValidator;

    private static final String DOCTORS_PATH = "/doctors";


    @Test
    @WithMockUser
    public void getDoctorsShouldInvokeServiceMethod() throws Exception {

        mockMvc.perform(get(DOCTORS_PATH))
                .andExpect(status().isOk());

        verify(doctorService).getDoctors();
    }


    @Test
    @WithMockUser
    public void getDoctorByIdShouldInvokeServiceMethod() throws Exception {

        mockMvc.perform(get(DOCTORS_PATH + "/1"))
                .andExpect(status().isOk());

        verify(doctorService).getDoctorById(1);
    }


    @Test
    @WithMockUser(roles = "CHIEF")
    public void addDoctorShouldInvokeServiceMethod() throws Exception {

        DoctorDto doctorDto = new DoctorDto();

        mockMvc.perform(post(DOCTORS_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(marshalize(doctorDto)))
                .andExpect(status().isOk());

        verify(doctorService).addDoctor(doctorDto);
    }


    @Test
    @WithMockUser(roles = "CHIEF")
    public void updateDoctorDescriptionShouldInvokeServiceMethod() throws Exception {

        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setId(1L);

        mockMvc.perform(put(DOCTORS_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(marshalize(doctorDto)))
                .andExpect(status().isOk());

        verify(doctorService).updateDoctorDescription(doctorDto);
    }


    @Test
    @WithMockUser(roles = "CHIEF")
    public void deleteDoctorShouldInvokeServiceMethod() throws Exception {

        mockMvc.perform(delete(DOCTORS_PATH + "/1"))
                .andExpect(status().isOk());

        verify(doctorService).deleteDoctorById(1);
    }

}
