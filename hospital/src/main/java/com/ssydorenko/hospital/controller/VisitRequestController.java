package com.ssydorenko.hospital.controller;

import com.ssydorenko.hospital.db.service.api.VisitRequestService;
import com.ssydorenko.hospital.domain.dto.VisitRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/requests")
public class VisitRequestController {

    @Autowired
    private VisitRequestService visitRequestService;


    @GetMapping("{requestId}")
    public VisitRequestDto getVisitRequestById(@PathVariable long requestId) {

        return visitRequestService.getVisitRequestById(requestId);
    }


    @GetMapping("/new")
    public List<VisitRequestDto> getNewVisitRequests() {

        return visitRequestService.getNewVisitRequests();
    }


    @PostMapping
    public void addVisitRequest(@RequestBody VisitRequestDto visitRequestDto) {

        visitRequestService.addVisitRequest(visitRequestDto);
    }


    @PutMapping
    public void changeStatusOfVisitRequest(@RequestBody VisitRequestDto visitRequestDto) {

        visitRequestService.changeStatusOfVisitRequest(visitRequestDto);
    }


    @DeleteMapping("{requestId}")
    public void deleteVisitRequest(@PathVariable long requestId) {

        visitRequestService.deleteVisitRequestById(requestId);
    }

}
