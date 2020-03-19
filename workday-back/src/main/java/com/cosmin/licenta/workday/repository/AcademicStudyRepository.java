package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.AcademicStudy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicStudyRepository extends JpaRepository<AcademicStudy, Long> {
}
