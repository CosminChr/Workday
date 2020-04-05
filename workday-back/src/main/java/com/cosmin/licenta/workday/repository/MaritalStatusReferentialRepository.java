package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.MaritalStatusReferential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MaritalStatusReferentialRepository extends JpaRepository<MaritalStatusReferential, Long> {

    Optional<MaritalStatusReferential> findByLabel(String label);
}
