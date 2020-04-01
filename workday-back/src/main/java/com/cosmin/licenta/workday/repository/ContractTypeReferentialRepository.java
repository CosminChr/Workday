package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.ContractTypeReferential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContractTypeReferentialRepository extends JpaRepository<ContractTypeReferential, Long> {
    Optional<ContractTypeReferential> findByLabel(String label);
}

