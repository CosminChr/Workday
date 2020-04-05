package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.Child;
import com.cosmin.licenta.workday.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChildRepository extends JpaRepository<Child, Long> {

    Optional<List<Child>> findByEmployee(Employee employee);
}
