package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.Overtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OvertimeRepository extends JpaRepository<Overtime, Long> {
}
