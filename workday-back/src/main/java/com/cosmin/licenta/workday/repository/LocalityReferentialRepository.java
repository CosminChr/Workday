package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.CountryReferential;
import com.cosmin.licenta.workday.entity.CountyReferential;
import com.cosmin.licenta.workday.entity.LocalityReferential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocalityReferentialRepository extends JpaRepository<LocalityReferential, Long> {

    Optional<LocalityReferential> findByLabelAndCountyAndCountry(String label, CountyReferential county, CountryReferential country);

}
