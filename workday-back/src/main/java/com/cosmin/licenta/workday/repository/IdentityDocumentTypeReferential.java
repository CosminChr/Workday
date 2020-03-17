package com.cosmin.licenta.workday.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdentityDocumentTypeReferential extends JpaRepository<IdentityDocumentTypeReferential, Long> {
}
