package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.Employee;
import com.cosmin.licenta.workday.entity.IdentityDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IdentityDocumentRepository extends JpaRepository<IdentityDocument, Long> {

    Optional<List<IdentityDocument>> findByEmployee(Employee employee);
}
