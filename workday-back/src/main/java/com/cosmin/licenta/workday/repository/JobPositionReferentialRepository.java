package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.JobPositionReferential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobPositionReferentialRepository extends JpaRepository<JobPositionReferential, Long> {

    Optional<JobPositionReferential> findByLabel(final String label);
}
