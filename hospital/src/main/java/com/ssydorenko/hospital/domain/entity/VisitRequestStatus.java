package com.ssydorenko.hospital.domain.entity;

import com.ssydorenko.hospital.domain.enums.RequestStatus;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;


@Data
@Entity
public class VisitRequestStatus {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private RequestStatus status;

    @Column
    private String statusDescription;

    @Column
    private LocalDateTime lastStatusChangeDateTime;

}
