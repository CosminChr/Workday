package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.RoleReferential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleReferentialRepository extends JpaRepository<RoleReferential, Long> {
    Optional<RoleReferential> findByLabel(String label);
}
