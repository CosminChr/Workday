package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.Employee;
import com.cosmin.licenta.workday.entity.PreviousJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PreviousJobRepository extends JpaRepository<PreviousJob, Long> {

    Optional<List<PreviousJob>> findByEmployee(Employee employee);
}
