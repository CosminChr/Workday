package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {
}
