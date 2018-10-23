package com.ssydorenko.hospital.controller;

import com.ssydorenko.hospital.db.service.VisitRequestService;
import com.ssydorenko.hospital.domain.dto.VisitRequestDto;
import com.ssydorenko.hospital.domain.enums.VisitRequestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/visit-requests")
public class VisitRequestController {

    @Autowired
    private VisitRequestService visitRequestService;


    @PostMapping("/doctor-id/{doctorId}")
    public void addVisitRequest(@PathVariable long doctorId, @RequestBody VisitRequestDto visitRequestDto) {

        visitRequestService.addVisitRequest(doctorId, visitRequestDto);
    }


    @PutMapping("{requestId}/{status}")
    public void changeStatusOfVisitRequest(@PathVariable long requestId,
            @PathVariable VisitRequestStatus status) {

        visitRequestService.changeStatusOfVisitRequest(requestId, status);
    }

    @GetMapping("{requestId}")
    public VisitRequestDto getVisitRequestById(@PathVariable long requestId) {

        return visitRequestService.getVisitRequestById(requestId);
    }

}
