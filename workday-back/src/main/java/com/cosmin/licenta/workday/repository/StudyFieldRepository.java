package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.StudyFieldReferential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyFieldRepository extends JpaRepository<StudyFieldReferential, Long> {
}
