package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.Citizenship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitizenshipRepository extends JpaRepository<Citizenship, Long> {
}