package com.ssydorenko.hospital.db.repository;

import com.ssydorenko.hospital.domain.entity.MedicalCardRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MedicalCardRecordRepository extends CrudRepository<MedicalCardRecord, Long> {

}
