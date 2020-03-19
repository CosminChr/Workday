package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.PreviousJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreviousJobRepository extends JpaRepository<PreviousJob, Long> {
}
