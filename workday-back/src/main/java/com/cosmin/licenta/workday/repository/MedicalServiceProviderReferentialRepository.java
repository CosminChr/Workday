package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.MedicalServiceProviderReferential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalServiceProviderReferentialRepository extends JpaRepository<MedicalServiceProviderReferential, Long> {

}
