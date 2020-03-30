package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.LanguageReferential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LanguageReferentialRepository extends JpaRepository<LanguageReferential, Long> {

    Optional<LanguageReferential> findByLabel(String label);
}
