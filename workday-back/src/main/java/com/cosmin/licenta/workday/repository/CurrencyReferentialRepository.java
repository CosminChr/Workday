package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.CurrencyReferential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrencyReferentialRepository extends JpaRepository<CurrencyReferential, Long> {

    Optional<CurrencyReferential> findByLabel(String string);

}
