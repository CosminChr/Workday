package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.CitizenshipReferential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CitizenshipReferentialRepository extends JpaRepository<CitizenshipReferential, Long> {
    Optional<CitizenshipReferential> findByLabel(String label);

}
