package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.JobDomainReferential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobDomainReferentialRepository extends JpaRepository<JobDomainReferential, Long> {
    Optional<JobDomainReferential> findByLabel(String label);
}
