package com.ssydorenko.hospital.domain.entity;

import com.ssydorenko.hospital.domain.enums.RequestStatus;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Data
public class VisitRequest {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String reason;

    @Column(nullable = false)
    private LocalDateTime requestedDatetime;

    @Column(nullable = false)
    private long doctorId;

    @Column(nullable = false)
    private RequestStatus status;

    @Column
    private String statusDescription;

    @Column(nullable = false)
    private LocalDateTime lastStatusChangeDateTime;

}
