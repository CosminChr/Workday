package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.MedicalServiceProviderReferential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicalServiceProviderReferentialRepository extends JpaRepository<MedicalServiceProviderReferential, Long> {
    Optional<MedicalServiceProviderReferential> findByLabel(String label);
}
