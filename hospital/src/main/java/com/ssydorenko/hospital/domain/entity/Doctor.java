package com.ssydorenko.hospital.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Doctor extends AbstractUser {

    @OneToOne
    private DoctorSchedule doctorSchedule;

    @Column(nullable = false)
    private String doctorDescription;

}
