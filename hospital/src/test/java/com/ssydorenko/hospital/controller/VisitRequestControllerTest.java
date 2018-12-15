package com.ssydorenko.hospital.controller;

import com.ssydorenko.hospital.db.service.api.VisitRequestService;
import com.ssydorenko.hospital.domain.dto.VisitRequestDto;
import com.ssydorenko.hospital.utils.validator.VisitRequestServiceValidator;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class VisitRequestControllerTest extends AbstractControllerTest {

    @MockBean
    private VisitRequestService visitRequestService;

    @MockBean
    private VisitRequestServiceValidator visitRequestServiceValidator;

    private static final String REQUESTS_PATH = "/requests";


    @Test
    @WithMockUser(roles = "CHIEF")
    public void getRequestByIdShouldInvokeServiceMethod() throws Exception {

        mockMvc.perform((get(REQUESTS_PATH + "/1")))
                .andExpect(status().isOk());

        verify(visitRequestService).getVisitRequestById(1);
    }


    @Test
    @WithMockUser(roles = "CHIEF")
    public void getNewRequestsShouldInvokeServiceMethod() throws Exception {

        mockMvc.perform((get(REQUESTS_PATH + "/new")))
                .andExpect(status().isOk());

        verify(visitRequestService).getNewVisitRequests();
    }


    @Test
    @WithMockUser(roles = "PATIENT")
    public void addRequestShouldInvokeServiceMethod() throws Exception {

        VisitRequestDto dto = new VisitRequestDto();
        dto.setDoctorId(1L);

        mockMvc.perform((post(REQUESTS_PATH))
                .contentType(MediaType.APPLICATION_JSON)
                .content(marshalize(dto)))
                .andExpect(status().isOk());

        verify(visitRequestService).addVisitRequest(dto);
    }


    @Test
    @WithMockUser(roles = "CHIEF")
    public void changeRequestShouldInvokeServiceMethod() throws Exception {

        VisitRequestDto dto = new VisitRequestDto();
        dto.setId(1L);

        mockMvc.perform((put(REQUESTS_PATH))
                .contentType(MediaType.APPLICATION_JSON)
                .content(marshalize(dto)))
                .andExpect(status().isOk());

        verify(visitRequestService).changeStatusOfVisitRequest(dto);
    }


    @Test
    @WithMockUser(roles = "CHIEF")
    public void getDoctorScheduleShouldInvokeServiceMethod() throws Exception {

        mockMvc.perform(get(REQUESTS_PATH + "/doctors/1/schedule"))
                .andExpect(status().isOk());

        verify(visitRequestService).getDoctorScheduleByDoctorId(1);
    }

}
