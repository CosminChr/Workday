package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.StudyFieldReferential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudyFieldReferentialRepository extends JpaRepository<StudyFieldReferential, Long> {
    Optional<StudyFieldReferential> findByLabel(String label);
}
