package com.cosmin.licenta.workday.service;

import com.cosmin.licenta.workday.dto.LocalityReferentialDTO;
import com.cosmin.licenta.workday.entity.LocalityReferential;
import com.cosmin.licenta.workday.mapper.LocalityReferentialMapper;
import com.cosmin.licenta.workday.repository.LocalityReferentialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalityReferentialService {

    private LocalityReferentialRepository localityReferentialRepository;

    private LocalityReferentialMapper localityReferentialMapper;

    public LocalityReferentialService(LocalityReferentialRepository localityReferentialRepository, LocalityReferentialMapper localityReferentialMapper) {
        this.localityReferentialRepository = localityReferentialRepository;
        this.localityReferentialMapper = localityReferentialMapper;
    }

    public List<LocalityReferentialDTO> getLocalityReferentials() {
        List<LocalityReferential> localityReferentials = localityReferentialRepository.findAll();
        return localityReferentialMapper.entitiesToDomains(localityReferentials);
    }
}
