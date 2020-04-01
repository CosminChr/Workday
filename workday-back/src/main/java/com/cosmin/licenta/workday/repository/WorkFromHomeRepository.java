package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.WorkFromHome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkFromHomeRepository extends JpaRepository<WorkFromHome, Long> {
}
