package com.ssydorenko.hospital.db.service.impl;

import com.ssydorenko.hospital.db.repository.VisitRequestRepository;
import com.ssydorenko.hospital.db.service.api.VisitRequestService;
import com.ssydorenko.hospital.domain.dto.VisitRequestDto;
import com.ssydorenko.hospital.domain.entity.VisitRequest;
import com.ssydorenko.hospital.domain.enums.RequestStatus;
import com.ssydorenko.hospital.utils.HospitalProperties;
import com.ssydorenko.hospital.utils.mapper.VisitRequestMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class VisitRequestServiceImpl implements VisitRequestService {

    @Autowired
    private VisitRequestRepository visitRequestRepository;

    @Autowired
    private VisitRequestMapper visitRequestMapper;

    @Autowired
    private HospitalProperties properties;


    @Override
    public void addVisitRequest(VisitRequestDto visitRequestDto) {

        List<VisitRequestDto> scheduleForDay = getDoctorScheduleByDoctorIdForSpecificDate(
                visitRequestDto.getDoctorId(), visitRequestDto.getRequestedDatetime().toLocalDate());

        checkIfRequestedTimeOccupied(visitRequestDto.getRequestedDatetime(), scheduleForDay);

        VisitRequest visitRequest = visitRequestMapper.toEntity(visitRequestDto);
        visitRequest.setDoctorId(visitRequestDto.getDoctorId());
        visitRequest.setStatus(RequestStatus.NEW);
        visitRequest.setLastStatusChangeDateTime(LocalDateTime.now());

        visitRequestRepository.save(visitRequest);
    }


    @Override
    public VisitRequestDto getVisitRequestById(long visitRequestId) {

        return visitRequestMapper.toDto(visitRequestRepository.getOne(visitRequestId));
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


    @Override
    public List<VisitRequestDto> getDoctorScheduleByDoctorIdForSpecificDate(long doctorId, LocalDate date) {

        LocalDateTime datetimeStart = LocalDateTime.of(date, properties.getWorkingDayStartTime());
        LocalDateTime datetimeEnd = LocalDateTime.of(date, properties.getWorkingDayEndTime());

        return visitRequestRepository.getApprovedRequestsByDoctorIdForSpecificDate(doctorId, datetimeStart, datetimeEnd)
                .stream()
                .map(visitRequestMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public List<VisitRequestDto> getDoctorScheduleByDoctorId(long doctorId) {

        return visitRequestRepository.getApprovedRequestsByDoctorId(doctorId)
                .stream()
                .map(visitRequestMapper::toDto)
                .collect(Collectors.toList());
    }


    private void checkIfRequestedTimeOccupied(LocalDateTime requestedTime, List<VisitRequestDto> scheduleForDay) {

        LocalDateTime intervalStart = requestedTime.minus(properties.getAverageAppointmentTimeMinutes(), ChronoUnit.MINUTES);
        LocalDateTime intervalEnd = requestedTime.plus(properties.getAverageAppointmentTimeMinutes(), ChronoUnit.MINUTES);

        boolean isOccupied = scheduleForDay.stream()
                .anyMatch(vr -> vr.getRequestedDatetime().isAfter(intervalStart) ||
                                vr.getRequestedDatetime().isBefore(intervalEnd));
        if (isOccupied) {

            throw new IllegalArgumentException("Sorry the requested time is occupied");
        }
    }

}
