package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.IdentityDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdentityDocumentRepository extends JpaRepository<IdentityDocument, Long> {
}
