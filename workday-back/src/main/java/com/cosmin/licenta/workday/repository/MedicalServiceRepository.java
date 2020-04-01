package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.MedicalService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalServiceRepository extends JpaRepository<MedicalService, Long> {

}
