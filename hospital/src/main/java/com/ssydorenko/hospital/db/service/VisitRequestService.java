package com.ssydorenko.hospital.db.service;

import com.ssydorenko.hospital.db.repository.VisitRequestRepository;
import com.ssydorenko.hospital.domain.dto.VisitRequestDto;
import com.ssydorenko.hospital.domain.entity.VisitRequest;
import com.ssydorenko.hospital.domain.enums.VisitRequestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ssydorenko.hospital.utils.converter.*;

@Service
public class VisitRequestService {

    @Autowired
    private VisitRequestRepository visitRequestRepository;

    @Autowired
    private VisitRequestMapper visitRequestMapper;


    public void addVisitRequest(long doctorId, VisitRequestDto visitRequestDto) {

        VisitRequest visitRequest = visitRequestMapper.toEntity(visitRequestDto);
        visitRequest.setDoctorId(doctorId);
        visitRequest.setStatus(VisitRequestStatus.NEW);
        visitRequestRepository.save(visitRequest);
    }


    public VisitRequestDto getVisitRequestById(long visitRequestId) {

        return visitRequestMapper.toDto(visitRequestRepository.getOne(visitRequestId));
    }


    public void deleteVisitRequestById(long visitRequestId) {

        visitRequestRepository.deleteById(visitRequestId);
    }


    public void changeStatusOfVisitRequest(long requestId, VisitRequestStatus status) {

        VisitRequest visitRequest = visitRequestRepository.getOne(requestId);
        visitRequest.setStatus(status);
        visitRequestRepository.save(visitRequest);
    }

}
