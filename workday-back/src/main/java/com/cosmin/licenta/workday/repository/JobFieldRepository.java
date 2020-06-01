package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.JobFieldReferential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobFieldRepository extends JpaRepository<JobFieldReferential, Long> {
    Optional<JobFieldReferential> findByLabel(String label);
}
