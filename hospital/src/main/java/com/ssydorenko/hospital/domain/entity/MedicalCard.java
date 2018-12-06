package com.ssydorenko.hospital.domain.entity;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;


@Entity
@Data
public class MedicalCard {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String patientDescription;

    @Column(nullable = false)
    private LocalDate dateOfRegistration;

    @OneToMany(mappedBy = "medicalCardId", cascade = CascadeType.REMOVE)
    private List<MedicalCardRecord> records;

}
