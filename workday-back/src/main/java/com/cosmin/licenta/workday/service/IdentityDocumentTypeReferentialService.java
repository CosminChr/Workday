package com.cosmin.licenta.workday.service;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.entity.IdentityDocumentTypeReferential;
import com.cosmin.licenta.workday.mapper.IdentityDocumentTypeReferentialMapper;
import com.cosmin.licenta.workday.repository.IdentityDocumentTypeReferentialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdentityDocumentTypeReferentialService {

    private final IdentityDocumentTypeReferentialRepository identityDocumentTypeReferentialRepository;

    private final IdentityDocumentTypeReferentialMapper identityDocumentTypeReferentialMapper;

    public IdentityDocumentTypeReferentialService(IdentityDocumentTypeReferentialRepository identityDocumentTypeReferentialRepository, IdentityDocumentTypeReferentialMapper identityDocumentTypeReferentialMapper) {
        this.identityDocumentTypeReferentialRepository = identityDocumentTypeReferentialRepository;
        this.identityDocumentTypeReferentialMapper = identityDocumentTypeReferentialMapper;
    }


    public List<ReferentialDTO> getIdentityDocumentTypeReferentials() {
        List<IdentityDocumentTypeReferential> identityDocumentTypeReferentials = identityDocumentTypeReferentialRepository.findAll();
        return identityDocumentTypeReferentialMapper.entitiesToDomains(identityDocumentTypeReferentials);
    }
}
