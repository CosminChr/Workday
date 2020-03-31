package com.cosmin.licenta.workday.service;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.entity.AddressTypeReferential;
import com.cosmin.licenta.workday.entity.LanguageLevelReferential;
import com.cosmin.licenta.workday.entity.LanguageReferential;
import com.cosmin.licenta.workday.mapper.LanguageLevelReferentialMapper;
import com.cosmin.licenta.workday.mapper.LanguageReferentialMapper;
import com.cosmin.licenta.workday.repository.LanguageLevelReferentialRepository;
import com.cosmin.licenta.workday.repository.LanguageReferentialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageReferentialService {

    private final LanguageReferentialRepository languageReferentialRepository;

    private final LanguageReferentialMapper languageReferentialMapper;

    public LanguageReferentialService(LanguageReferentialRepository languageReferentialRepository, LanguageReferentialMapper languageReferentialMapper) {
        this.languageReferentialRepository = languageReferentialRepository;
        this.languageReferentialMapper = languageReferentialMapper;
    }

    public List<ReferentialDTO> getLanguageRefs() {
        List<LanguageReferential> languageReferentials = languageReferentialRepository.findAll();
        return languageReferentialMapper.entitiesToDomains(languageReferentials);
    }
}
