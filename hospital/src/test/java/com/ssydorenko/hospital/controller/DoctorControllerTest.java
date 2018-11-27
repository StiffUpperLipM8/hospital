package com.ssydorenko.hospital.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssydorenko.hospital.db.service.api.DoctorService;
import com.ssydorenko.hospital.domain.dto.DoctorDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DoctorControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DoctorService doctorService;


    @Test
    @WithMockUser
    public void getDoctorsShouldInvokeServiceMethod() throws Exception {

        mockMvc.perform(get("/doctors"))
                .andExpect(status().isOk());
        verify(doctorService).getDoctors();
    }

    @Test
    @WithMockUser
    public void getDoctorByIdShouldInvokeServiceMethod() throws Exception {

        mockMvc.perform(get("/doctors/1"))
                .andExpect(status().isOk());
        verify(doctorService).getDoctorById(1);
    }

    @Test
    @WithMockUser
    public void getDoctorScheduleShouldInvokeServiceMethod() throws Exception {

        mockMvc.perform(get("/doctors/1/schedule"))
                .andExpect(status().isOk());
        verify(doctorService).getDoctorScheduleByDoctorId(1);
    }

    @Test
    @WithMockUser(roles = "CHIEF")
    public void addDoctorShouldInvokeServiceMethod() throws Exception {

        DoctorDto doctorDto = new DoctorDto();

        mockMvc.perform(post("/doctors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(doctorDto)))
                .andExpect(status().isOk());

        verify(doctorService).addDoctor(doctorDto);
    }

    @Test
    @WithMockUser(roles = "CHIEF")
    public void updateDoctorDescriptionShouldInvokeServiceMethod() throws Exception {

        DoctorDto doctorDto = new DoctorDto();

        mockMvc.perform(put("/doctors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(doctorDto)))
                .andExpect(status().isOk());

        verify(doctorService).updateDoctorDescription(doctorDto);
    }

    @Test
    @WithMockUser(roles = "CHIEF")
    public void deleteDoctorShouldInvokeServiceMethod() throws Exception {

        DoctorDto doctorDto = new DoctorDto();

        mockMvc.perform(delete("/doctors/1"))
                .andExpect(status().isOk());

        verify(doctorService).deleteDoctorById(1);
    }

}
