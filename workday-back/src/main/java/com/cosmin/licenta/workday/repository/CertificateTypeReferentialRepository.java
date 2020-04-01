package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.CertificateTypeReferential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificateTypeReferentialRepository extends JpaRepository<CertificateTypeReferential, Long> {

}

