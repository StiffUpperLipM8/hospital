package com.ssydorenko.hospital.db.repository;

import com.ssydorenko.hospital.domain.entity.DoctorSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DoctorScheduleRepository extends JpaRepository<DoctorSchedule, Long> {

    DoctorSchedule findByDoctorId(long doctorId);

}
