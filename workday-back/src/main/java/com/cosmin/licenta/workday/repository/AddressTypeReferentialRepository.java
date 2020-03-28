package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.AddressTypeReferential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressTypeReferentialRepository extends JpaRepository<AddressTypeReferential, Long> {

    Optional<AddressTypeReferential> findByLabel(String string);
}
