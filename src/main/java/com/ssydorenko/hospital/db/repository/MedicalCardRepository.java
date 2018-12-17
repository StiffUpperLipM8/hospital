package com.ssydorenko.hospital.db.repository;

import com.ssydorenko.hospital.domain.entity.MedicalCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MedicalCardRepository extends JpaRepository<MedicalCard, Long> {

}
