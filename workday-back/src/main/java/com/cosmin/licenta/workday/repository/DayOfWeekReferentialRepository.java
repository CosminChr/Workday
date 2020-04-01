package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.DayOfWeekReferential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DayOfWeekReferentialRepository extends JpaRepository<DayOfWeekReferential, Long> {

}
