package com.ssydorenko.hospital.db.service.impl;


import com.ssydorenko.hospital.db.repository.DoctorRepository;
import com.ssydorenko.hospital.db.repository.VisitRequestRepository;
import com.ssydorenko.hospital.db.service.api.VisitRequestService;
import com.ssydorenko.hospital.domain.dto.VisitRequestDto;
import com.ssydorenko.hospital.domain.entity.VisitRequest;
import com.ssydorenko.hospital.domain.enums.RequestStatus;
import com.ssydorenko.hospital.utils.mapper.VisitRequestMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VisitRequestServiceTest {

    @MockBean
    private VisitRequestRepository visitRequestRepository;

    @MockBean
    private DoctorRepository doctorRepository;

    @Autowired
    private VisitRequestMapper visitRequestMapper;

    @Autowired
    private VisitRequestService visitRequestService;

    private static final long TEST_ID = 1L;


    @Test(expected = IllegalArgumentException.class)
    public void addRequestWithNonexistentDoctorIdShouldThrowException() {

        visitRequestService.addVisitRequest(new VisitRequestDto());
    }

    @Test
    public void addRequestShouldAddRequestWithParametersSet() {

        ArgumentCaptor<VisitRequest> captor = ArgumentCaptor.forClass(VisitRequest.class);
        VisitRequestDto dto = generateVisitRequestDto();
        when(doctorRepository.existsById(TEST_ID)).thenReturn(true);

        visitRequestService.addVisitRequest(dto);

        verify(visitRequestRepository).save(captor.capture());
        assertEquals(RequestStatus.NEW, captor.getValue().getStatus());
        assertNotNull(captor.getValue().getLastStatusChangeDateTime());
    }


    @Test
    public void getRequestByIdShouldReturnRequestDto() {

        when(visitRequestRepository.getOne(TEST_ID)).thenReturn(generateVisitRequest());
        VisitRequestDto dto = visitRequestService.getVisitRequestById(TEST_ID);
        assertNotNull(dto);
    }


    @Test
    public void deleteRequestByIdShouldDeleteRequest() {

        visitRequestService.deleteVisitRequestById(TEST_ID);
        verify(visitRequestRepository).deleteById(TEST_ID);
    }


    @Test
    public void changeStatusOfRequestSetUpdateStatus() {

        ArgumentCaptor<VisitRequest> captor = ArgumentCaptor.forClass(VisitRequest.class);
        VisitRequest visitRequest = generateVisitRequest();
        when(visitRequestRepository.getOne(TEST_ID)).thenReturn(visitRequest);

        visitRequestService.changeStatusOfVisitRequest(generateVisitRequestDto());

        verify(visitRequestRepository).save(captor.capture());
        assertEquals(RequestStatus.APPROVED, captor.getValue().getStatus());
    }


    private VisitRequestDto generateVisitRequestDto() {

        VisitRequestDto dto = new VisitRequestDto();
        dto.setId(TEST_ID);
        dto.setDoctorId(TEST_ID);
        dto.setDesiredDatetime(LocalDateTime.now());
        dto.setStatus(RequestStatus.APPROVED);
        dto.setReason("test");
        return dto;
    }

    private VisitRequest generateVisitRequest() {

        VisitRequest visitRequest = new VisitRequest();
        visitRequest.setStatus(RequestStatus.NEW);
        visitRequest.setReason("test");
        visitRequest.setDoctorId(TEST_ID);
        visitRequest.setLastStatusChangeDateTime(LocalDateTime.now());
        return visitRequest;
    }
}