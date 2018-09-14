package com.ssydorenko.hospital.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class ChiefMedicalOfficer extends AbstractUser {

    @OneToMany
    private List<DoctorSchedule> doctorSchedules;

}
