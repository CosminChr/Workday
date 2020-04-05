package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.StudyLevelReferential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudyLevelReferentialRepository extends JpaRepository<StudyLevelReferential, Long> {

    Optional<StudyLevelReferential> findByLabel(String label);
}
