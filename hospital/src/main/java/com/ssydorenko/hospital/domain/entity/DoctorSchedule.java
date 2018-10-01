package com.ssydorenko.hospital.domain.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;


@Entity
@Data
public class DoctorSchedule {

    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    private Doctor doctor;

    @OneToMany(mappedBy = "doctorSchedule")
    private List<VisitRequest> schedule;

}
