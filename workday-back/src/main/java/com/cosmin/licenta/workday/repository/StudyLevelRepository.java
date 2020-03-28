package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.StudyLevelReferential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyLevelRepository extends JpaRepository<StudyLevelReferential, Long> {
}