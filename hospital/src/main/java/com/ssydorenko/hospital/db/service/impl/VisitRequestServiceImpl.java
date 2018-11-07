package com.ssydorenko.hospital.db.service.impl;

import com.ssydorenko.hospital.db.repository.DoctorRepository;
import com.ssydorenko.hospital.db.repository.VisitRequestRepository;
import com.ssydorenko.hospital.db.service.api.VisitRequestService;
import com.ssydorenko.hospital.domain.dto.VisitRequestDto;
import com.ssydorenko.hospital.domain.entity.VisitRequest;
import com.ssydorenko.hospital.domain.enums.RequestStatus;
import com.ssydorenko.hospital.utils.mapper.VisitRequestMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class VisitRequestServiceImpl implements VisitRequestService {

    @Autowired
    private VisitRequestRepository visitRequestRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private VisitRequestMapper visitRequestMapper;


    @Override
    public void addVisitRequest(VisitRequestDto visitRequestDto) {

        if (!doctorRepository.existsById(visitRequestDto.getDoctorId())) {

            throw new IllegalArgumentException("Doctor with id: " + visitRequestDto.getDoctorId() + " does not exist");
        }

        VisitRequest visitRequest = visitRequestMapper.toEntity(visitRequestDto);

        visitRequest.setDoctorId(visitRequestDto.getDoctorId());
        visitRequest.setStatus(RequestStatus.NEW);
        visitRequest.setLastStatusChangeDateTime(LocalDateTime.now());

        visitRequestRepository.save(visitRequest);
    }


    @Override
    public VisitRequestDto getVisitRequestById(long visitRequestId) {

        VisitRequest visitRequest = visitRequestRepository.getOne(visitRequestId);
        return visitRequestMapper.toDto(visitRequest);
    }


    @Override
    @Transactional
    public void deleteVisitRequestById(long visitRequestId) {

        visitRequestRepository.deleteById(visitRequestId);
    }


    @Override
    @Transactional
    public void changeStatusOfVisitRequest(VisitRequestDto visitRequestDto) {

        VisitRequest visitRequest = visitRequestRepository.getOne(visitRequestDto.getId());

        visitRequest.setStatus(visitRequestDto.getStatus());
        visitRequest.setLastStatusChangeDateTime(LocalDateTime.now());

        if (StringUtils.isNotBlank(visitRequestDto.getStatusDescription())) {
            visitRequest.setStatusDescription(visitRequestDto.getStatusDescription());
        }

        visitRequestRepository.save(visitRequest);
    }


    @Override
    public List<VisitRequestDto> getNewVisitRequests() {

        return visitRequestRepository.getNewVisitRequests()
                .stream()
                .map(visitRequestMapper::toDto)
                .collect(Collectors.toList());
    }

}
