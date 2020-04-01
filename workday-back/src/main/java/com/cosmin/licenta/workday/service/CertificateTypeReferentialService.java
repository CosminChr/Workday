package com.cosmin.licenta.workday.service;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.entity.CertificateTypeReferential;
import com.cosmin.licenta.workday.mapper.CertificateTypeReferentialMapper;
import com.cosmin.licenta.workday.repository.CertificateTypeReferentialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificateTypeReferentialService {

    private final CertificateTypeReferentialRepository certificateTypeReferentialRepository;

    private final CertificateTypeReferentialMapper certificateTypeReferentialMapper;

    public CertificateTypeReferentialService(CertificateTypeReferentialRepository certificateTypeReferentialRepository, CertificateTypeReferentialMapper certificateTypeReferentialMapper) {
        this.certificateTypeReferentialRepository = certificateTypeReferentialRepository;
        this.certificateTypeReferentialMapper = certificateTypeReferentialMapper;
    }


    public List<ReferentialDTO> getCertificateTypeReferentials() {
        List<CertificateTypeReferential> certificateTypeReferentials = certificateTypeReferentialRepository.findAll();
        return certificateTypeReferentialMapper.entitiesToDomains(certificateTypeReferentials);
    }
}
