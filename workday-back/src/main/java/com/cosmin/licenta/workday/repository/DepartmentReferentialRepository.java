package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.DepartmentReferential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentReferentialRepository extends JpaRepository<DepartmentReferential, Long> {

    Optional<DepartmentReferential> findByLabel(final String label);
}
