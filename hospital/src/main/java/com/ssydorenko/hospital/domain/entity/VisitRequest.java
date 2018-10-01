package com.ssydorenko.hospital.domain.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;


@Entity
@Data
public class VisitRequest {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private DoctorSchedule doctorSchedule;

    @Column(nullable = false)
    private String reason;

    @Column
    private LocalDateTime desiredDatetime;

}
