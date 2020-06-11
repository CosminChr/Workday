package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Long> {

    @Query("SELECT h FROM Holiday h WHERE h.employee.id = ?1")
    Optional<List<Holiday>> findByEmployeeId(final Long employeeId);

    @Query("SELECT h FROM Holiday h WHERE h.employee.id IN ?1")
    Optional<List<Holiday>> findByEmployeeId(final List<Long> employeeIds);

    @Query("SELECT h FROM Holiday h WHERE h.employee.managerId = ?1")
    Optional<List<Holiday>> findByManagerId(final Long managerId);
}
