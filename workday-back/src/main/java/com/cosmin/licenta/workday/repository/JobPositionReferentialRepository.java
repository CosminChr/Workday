package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.JobPositionReferential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobPositionReferentialRepository extends JpaRepository<JobPositionReferential, Long> {

    Optional<JobPositionReferential> findByLabel(final String label);
}
