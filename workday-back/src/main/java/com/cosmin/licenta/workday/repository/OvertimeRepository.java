package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.Employee;
import com.cosmin.licenta.workday.entity.Holiday;
import com.cosmin.licenta.workday.entity.Overtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OvertimeRepository extends JpaRepository<Overtime, Long> {
    Optional<List<Overtime>> findByEmployee(Employee employee);

    @Query("SELECT o FROM Overtime o WHERE o.employee.managerId = ?1")
    Optional<List<Overtime>> findByManagerId(final Long managerId);
}
