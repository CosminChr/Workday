package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.DepartmentReferential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentReferentialRepository extends JpaRepository<DepartmentReferential, Long> {

    Optional<DepartmentReferential> findByLabel(final String label);
}
