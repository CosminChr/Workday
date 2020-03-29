package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.Employee;
import com.cosmin.licenta.workday.entity.MaritalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MaritalStatusRepository extends JpaRepository<MaritalStatus, Long> {

    Optional<MaritalStatus> findByEmployee(final Employee employee);

}
