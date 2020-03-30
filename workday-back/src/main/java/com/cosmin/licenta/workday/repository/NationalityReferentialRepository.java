package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.NationalityReferential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NationalityReferentialRepository extends JpaRepository<NationalityReferential, Long> {
}
