package com.cosmin.licenta.workday.service;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.entity.CountryReferential;
import com.cosmin.licenta.workday.entity.CurrencyReferential;
import com.cosmin.licenta.workday.mapper.CountryReferentialMapper;
import com.cosmin.licenta.workday.repository.CountryReferentialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryReferentialService {

    private final CountryReferentialRepository countryReferentialRepository;

    private final CountryReferentialMapper countryReferentialMapper;

    public CountryReferentialService(CountryReferentialRepository countryReferentialRepository, CountryReferentialMapper countryReferentialMapper) {
        this.countryReferentialRepository = countryReferentialRepository;
        this.countryReferentialMapper = countryReferentialMapper;
    }


    public List<ReferentialDTO> getCountryReferentials() {
        List<CountryReferential> countryReferentials = countryReferentialRepository.findAll();
        return countryReferentialMapper.entitiesToDomains(countryReferentials);
    }
}
