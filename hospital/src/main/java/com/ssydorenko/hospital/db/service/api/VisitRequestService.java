package com.ssydorenko.hospital.db.service.api;

import com.ssydorenko.hospital.domain.dto.VisitRequestDto;

import java.time.LocalDate;
import java.util.List;


public interface VisitRequestService {

    void addVisitRequest(VisitRequestDto visitRequestDto);

    VisitRequestDto getVisitRequestById(long visitRequestId);

    void deleteVisitRequestById(long visitRequestId);

    void changeStatusOfVisitRequest(VisitRequestDto visitRequestDto);

    List<VisitRequestDto> getNewVisitRequests();

    List<VisitRequestDto> getDoctorScheduleByDoctorIdForSpecificDate(long doctorId, LocalDate date);

    List<VisitRequestDto> getDoctorScheduleByDoctorId(long doctorId);

}
