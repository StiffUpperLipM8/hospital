package com.ssydorenko.hospital.db.repository;

import com.ssydorenko.hospital.domain.entity.VisitRequestStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VisitRequestStatusRepository extends CrudRepository<VisitRequestStatus, Long> {

}
