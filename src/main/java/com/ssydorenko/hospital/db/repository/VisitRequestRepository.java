package com.ssydorenko.hospital.db.repository;

import com.ssydorenko.hospital.domain.entity.VisitRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface VisitRequestRepository extends JpaRepository<VisitRequest, Long> {

    @Query("SELECT vr FROM VisitRequest vr WHERE vr.status = 1 AND vr.doctorId = :doctorId")
    List<VisitRequest> getApprovedRequestsByDoctorId(@Param("doctorId") long doctorId);


    @Query("SELECT vr FROM VisitRequest vr WHERE vr.status = 0")
    List<VisitRequest> getNewVisitRequests();


    @Query("SELECT vr FROM VisitRequest vr WHERE vr.status = 1 AND vr.doctorId = :doctorId " +
           "AND vr.requestedDatetime BETWEEN :dateTimeStart AND :dateTimeEnd")
    List<VisitRequest> getApprovedRequestsByDoctorIdForSpecificDate(@Param("doctorId") long doctorId,
                                                                    @Param("dateTimeStart")LocalDateTime dateTimeStart,
                                                                    @Param("dateTimeEnd") LocalDateTime dateTimeEnd);
}
