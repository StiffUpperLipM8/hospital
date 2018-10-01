package com.ssydorenko.hospital.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class ChiefMedicalOfficer extends AbstractUser {


}
