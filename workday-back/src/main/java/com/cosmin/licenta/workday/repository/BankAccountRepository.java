package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
}
