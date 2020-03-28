package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.CountyReferential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountyReferentialRepository extends JpaRepository<CountyReferential, Long> {
    Optional<CountyReferential> findByLabel(String label);
}
