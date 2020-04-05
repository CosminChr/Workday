package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.ContractTypeReferential;
import com.cosmin.licenta.workday.entity.RequiredExperienceReferential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RequiredExperienceReferentialRepository extends JpaRepository<RequiredExperienceReferential, Long> {
    Optional<RequiredExperienceReferential> findByLabel(String label);
}
