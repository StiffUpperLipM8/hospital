package com.ssydorenko.hospital.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;


@Entity
@Data
public class DoctorSchedule {

    @Id
    @GeneratedValue
    private long id;

    @OneToMany(mappedBy = "doctorSchedule")
    private List<VisitRequest> schedule;

}
