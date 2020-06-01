package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.HolidayReferential;
import com.cosmin.licenta.workday.entity.RoleReferential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HolidayReferentialRepository extends JpaRepository<HolidayReferential, Long> {
    Optional<HolidayReferential> findByLabel(String label);
}
