package com.ssydorenko.hospital.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.OneToOne;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Patient extends AbstractUser {

    @OneToOne
    private MedicalCard medicalCard;

}
