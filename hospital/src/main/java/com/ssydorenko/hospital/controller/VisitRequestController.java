package com.ssydorenko.hospital.controller;

import com.ssydorenko.hospital.db.service.api.VisitRequestService;
import com.ssydorenko.hospital.domain.dto.VisitRequestDto;
import com.ssydorenko.hospital.utils.validator.VisitRequestServiceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/requests")
public class VisitRequestController {

    @Autowired
    private VisitRequestService visitRequestService;

    @Autowired
    private VisitRequestServiceValidator visitRequestServiceValidator;

    @GetMapping("{requestId}")
    public VisitRequestDto getVisitRequestById(@PathVariable long requestId) {

        visitRequestServiceValidator.validateVisitRequestIdExists(requestId);
        return visitRequestService.getVisitRequestById(requestId);
    }


    @GetMapping("/new")
    public List<VisitRequestDto> getNewVisitRequests() {

        return visitRequestService.getNewVisitRequests();
    }


    @PostMapping
    public void addVisitRequest(@RequestBody VisitRequestDto visitRequestDto) {

        visitRequestServiceValidator.validateAddRequest(visitRequestDto);
        visitRequestService.addVisitRequest(visitRequestDto);
    }


    @PutMapping
    public void changeStatusOfVisitRequest(@RequestBody VisitRequestDto visitRequestDto) {

        visitRequestServiceValidator.validateVisitRequestIdExists(visitRequestDto.getId());
        visitRequestService.changeStatusOfVisitRequest(visitRequestDto);
    }


    @DeleteMapping("{requestId}")
    public void deleteVisitRequest(@PathVariable long requestId) {

        visitRequestServiceValidator.validateVisitRequestIdExists(requestId);
        visitRequestService.deleteVisitRequestById(requestId);
    }

}
