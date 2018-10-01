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
public class MedicalCardRecord {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @Column(nullable = false)
    private String text;

    @ManyToOne
    private Doctor doctor;

    @ManyToOne
    private MedicalCard medicalCard;

}
