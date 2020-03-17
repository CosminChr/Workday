package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.MaritalStatusReferential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaritalStatusReferentialRepository extends JpaRepository<MaritalStatusReferential, Long> {
}
