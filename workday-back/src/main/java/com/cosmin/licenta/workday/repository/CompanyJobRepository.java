package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.CompanyJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyJobRepository extends JpaRepository<CompanyJob, Long> {
}
