package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Long> {

}
