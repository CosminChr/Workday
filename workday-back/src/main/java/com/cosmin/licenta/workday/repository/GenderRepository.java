package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.Admin;
import com.cosmin.licenta.workday.entity.GenderReferential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenderRepository extends JpaRepository<GenderReferential, Long> {

    Optional<GenderReferential> findByLabel(final String label);
}
