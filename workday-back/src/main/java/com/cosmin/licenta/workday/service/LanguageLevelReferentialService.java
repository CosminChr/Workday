package com.cosmin.licenta.workday.service;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.entity.AddressTypeReferential;
import com.cosmin.licenta.workday.entity.LanguageLevelReferential;
import com.cosmin.licenta.workday.mapper.LanguageLevelReferentialMapper;
import com.cosmin.licenta.workday.repository.LanguageLevelReferentialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageLevelReferentialService {

    private final LanguageLevelReferentialRepository languageLevelReferentialRepository;

    private final LanguageLevelReferentialMapper languageLevelReferentialMapper;

    public LanguageLevelReferentialService(LanguageLevelReferentialRepository languageLevelReferentialRepository, LanguageLevelReferentialMapper languageLevelReferentialMapper) {
        this.languageLevelReferentialRepository = languageLevelReferentialRepository;
        this.languageLevelReferentialMapper = languageLevelReferentialMapper;
    }


    public List<ReferentialDTO> getLanguageLevelRefs() {
        List<LanguageLevelReferential> languageLevelReferentials = languageLevelReferentialRepository.findAll();
        return languageLevelReferentialMapper.entitiesToDomains(languageLevelReferentials);
    }
}
