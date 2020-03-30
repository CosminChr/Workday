package com.cosmin.licenta.workday.service;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.entity.NationalityReferential;
import com.cosmin.licenta.workday.mapper.NationalityReferentialMapper;
import com.cosmin.licenta.workday.repository.NationalityReferentialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NationalityReferentialService {

    private final NationalityReferentialRepository nationalityReferentialRepository;

    private final NationalityReferentialMapper nationalityReferentialMapper;

    public NationalityReferentialService(NationalityReferentialRepository nationalityReferentialRepository, NationalityReferentialMapper nationalityReferentialMapper) {
        this.nationalityReferentialRepository = nationalityReferentialRepository;
        this.nationalityReferentialMapper = nationalityReferentialMapper;
    }


    public List<ReferentialDTO> getNationalityReferentials() {
        List<NationalityReferential> nationalityReferentials = nationalityReferentialRepository.findAll();
        return nationalityReferentialMapper.entitiesToDomains(nationalityReferentials);
    }
}
