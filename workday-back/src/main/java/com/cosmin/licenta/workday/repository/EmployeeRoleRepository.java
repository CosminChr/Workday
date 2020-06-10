package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.EmployeeRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRoleRepository extends JpaRepository<EmployeeRole, Long> {

    @Query(value = "SELECT er FROM EmployeeRole  er WHERE er.id.employeeId = ?1")
    Optional<List<EmployeeRole>> findRolesForEmployee(Long employeeId);
}
