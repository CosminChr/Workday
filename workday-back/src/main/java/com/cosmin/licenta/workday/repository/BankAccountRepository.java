package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.BankAccount;
import com.cosmin.licenta.workday.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    Optional<List<BankAccount>> findByEmployee(final Employee employee);
}
