package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.LanguageLevelReferential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageLevelRepository extends JpaRepository<LanguageLevelReferential, Long> {
}
