package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.LanguageLevelReferential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LanguageLevelReferentialRepository extends JpaRepository<LanguageLevelReferential, Long> {

    Optional<LanguageLevelReferential> findByLabel(String label);
}
