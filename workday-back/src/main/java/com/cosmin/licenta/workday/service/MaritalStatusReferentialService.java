package com.cosmin.licenta.workday.service;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.entity.MaritalStatusReferential;
import com.cosmin.licenta.workday.mapper.MaritalStatusReferentialMapper;
import com.cosmin.licenta.workday.repository.MaritalStatusReferentialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaritalStatusReferentialService {

    private final MaritalStatusReferentialRepository maritalStatusReferentialRepository;

    private final MaritalStatusReferentialMapper maritalStatusReferentialMapper;

    public MaritalStatusReferentialService(MaritalStatusReferentialRepository maritalStatusReferentialRepository, MaritalStatusReferentialMapper maritalStatusReferentialMapper) {
        this.maritalStatusReferentialRepository = maritalStatusReferentialRepository;

        this.maritalStatusReferentialMapper = maritalStatusReferentialMapper;
    }

    public List<ReferentialDTO> getMaritalStatusReferentials() {
        List<MaritalStatusReferential> maritalStatusReferentials = maritalStatusReferentialRepository.findAll();
        return maritalStatusReferentialMapper.entitiesToDomains(maritalStatusReferentials);
    }
}
