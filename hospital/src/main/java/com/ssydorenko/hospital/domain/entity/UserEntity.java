package com.ssydorenko.hospital.domain.entity;

import com.ssydorenko.hospital.domain.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    private String fullName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private UserRole role;

}
