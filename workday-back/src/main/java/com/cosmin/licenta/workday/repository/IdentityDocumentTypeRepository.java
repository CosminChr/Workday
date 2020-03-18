package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.IdentityDocumentTypeReferential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdentityDocumentTypeRepository extends JpaRepository<IdentityDocumentTypeReferential, Long> {
}
