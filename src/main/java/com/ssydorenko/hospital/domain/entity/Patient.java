package com.ssydorenko.hospital.domain.entity;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
@Data
public class Patient {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @OneToOne(cascade = CascadeType.ALL)
    private MedicalCard medicalCard;

}
