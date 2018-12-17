package com.ssydorenko.hospital.domain.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;


@Entity
@Data
public class MedicalCardRecord {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private LocalDateTime dateOfCreation;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private long doctorId;

    @Column(nullable = false)
    private long medicalCardId;

}
