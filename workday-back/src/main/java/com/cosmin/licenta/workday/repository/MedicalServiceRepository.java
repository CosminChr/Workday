package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.Employee;
import com.cosmin.licenta.workday.entity.MedicalService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicalServiceRepository extends JpaRepository<MedicalService, Long> {
    Optional<MedicalService> findByEmployee(Employee employee);
}
