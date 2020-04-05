package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.IdentityDocumentTypeReferential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IdentityDocumentTypeReferentialRepository extends JpaRepository<IdentityDocumentTypeReferential, Long> {


    Optional<IdentityDocumentTypeReferential> findByLabel(String string);
}
