package com.ssydorenko.hospital.db.service;

import com.ssydorenko.hospital.db.repository.VisitRequestRepository;
import com.ssydorenko.hospital.domain.dto.VisitRequestDto;
import com.ssydorenko.hospital.domain.entity.VisitRequest;
import com.ssydorenko.hospital.domain.entity.VisitRequestStatus;
import com.ssydorenko.hospital.domain.enums.RequestStatus;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ssydorenko.hospital.utils.mapper.*;

import java.time.LocalDateTime;


@Service
public class VisitRequestService {

    @Autowired
    private VisitRequestRepository visitRequestRepository;

    @Autowired
    private VisitRequestMapper visitRequestMapper;


    public void addVisitRequest(long doctorId, VisitRequestDto visitRequestDto) {

        VisitRequest visitRequest = visitRequestMapper.toEntity(visitRequestDto);
        visitRequest.setDoctorId(doctorId);

        VisitRequestStatus visitRequestStatus = new VisitRequestStatus();
        visitRequestStatus.setStatus(RequestStatus.NEW);
        visitRequestStatus.setLastStatusChangeDateTime(LocalDateTime.now());

        visitRequestRepository.save(visitRequest);
    }


    public VisitRequestDto getVisitRequestById(long visitRequestId) {

        return visitRequestMapper.toDto(visitRequestRepository.getOne(visitRequestId));
    }


    public void deleteVisitRequestById(long visitRequestId) {

        visitRequestRepository.deleteById(visitRequestId);
    }


    public void changeStatusOfVisitRequest(long requestId, VisitRequestDto visitRequestDto) {

        VisitRequest visitRequest = visitRequestRepository.getOne(requestId);

        VisitRequestStatus visitRequestStatus = visitRequest.getVisitRequestStatus();
        visitRequestStatus.setStatus(visitRequestDto.getStatus());
        visitRequestStatus.setLastStatusChangeDateTime(LocalDateTime.now());

        if (isNotBlank(visitRequestDto.getStatusDescription())) {
            visitRequestStatus.setStatusDescription(visitRequestDto.getStatusDescription());
        }

        visitRequestRepository.save(visitRequest);
    }

    private boolean isNotBlank(String text) {

        return StringUtils.isNotBlank(text);
    }

}
