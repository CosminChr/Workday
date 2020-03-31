package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.AcademicStudy;
import com.cosmin.licenta.workday.entity.Citizenship;
import com.cosmin.licenta.workday.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AcademicStudyRepository extends JpaRepository<AcademicStudy, Long> {

    Optional<List<AcademicStudy>> findByEmployee(Employee employee);
}
