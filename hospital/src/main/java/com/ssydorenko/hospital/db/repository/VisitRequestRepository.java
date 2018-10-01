package com.ssydorenko.hospital.db.repository;

import com.ssydorenko.hospital.domain.entity.VisitRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VisitRequestRepository extends JpaRepository<VisitRequest, Long> {

}
