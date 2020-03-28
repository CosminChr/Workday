package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.CountryReferential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryReferentialRepository extends JpaRepository<CountryReferential, Long> {
    Optional<CountryReferential> findByLabel(String label);
}
