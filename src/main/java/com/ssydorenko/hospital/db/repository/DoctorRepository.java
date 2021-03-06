package com.ssydorenko.hospital.db.repository;

import com.ssydorenko.hospital.domain.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Doctor findByFullName(String fullName);

}
