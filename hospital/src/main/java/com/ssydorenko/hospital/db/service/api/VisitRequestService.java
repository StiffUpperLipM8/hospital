package com.ssydorenko.hospital.db.service.api;

import com.ssydorenko.hospital.domain.dto.VisitRequestDto;


public interface VisitRequestService {

    void addVisitRequest(VisitRequestDto visitRequestDto);

    VisitRequestDto getVisitRequestById(long visitRequestId);

    void deleteVisitRequestById(long visitRequestId);

    void changeStatusOfVisitRequest(long requestId, VisitRequestDto visitRequestDto);

}
