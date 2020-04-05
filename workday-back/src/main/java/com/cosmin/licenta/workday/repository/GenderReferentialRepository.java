package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.GenderReferential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenderReferentialRepository extends JpaRepository<GenderReferential, Long> {

    Optional<GenderReferential> findByLabel(final String label);
}
