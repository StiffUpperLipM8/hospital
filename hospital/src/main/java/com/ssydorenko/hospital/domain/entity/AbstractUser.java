package com.ssydorenko.hospital.domain.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


@Data
@MappedSuperclass
public abstract class AbstractUser {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

}
