package com.ssydorenko.hospital.domain.entity;

import lombok.Data;
import lombok.experimental.Delegate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
public class Doctor {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String doctorDescription;

    @OneToMany(mappedBy = "doctorId", cascade = CascadeType.ALL)
    @Delegate
    List<VisitRequest> schedule = new ArrayList<>();

}
