package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.NationalityReferential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NationalityReferentialRepository extends JpaRepository<NationalityReferential, Long> {

    @Query("SELECT n FROM NationalityReferential n WHERE n.id = ?1")
    Optional<NationalityReferential> findByNationalityId(Long nationalityId);
}
