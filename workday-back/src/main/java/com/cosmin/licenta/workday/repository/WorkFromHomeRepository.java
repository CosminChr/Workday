package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.Employee;
import com.cosmin.licenta.workday.entity.WorkFromHome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkFromHomeRepository extends JpaRepository<WorkFromHome, Long> {
    Optional<WorkFromHome> findByEmployee(Employee employee);

    @Query("SELECT w FROM WorkFromHome  w WHERE w.employee.managerId = ?1")
    Optional<List<WorkFromHome>> findByManagerId(Long managerId);
}
