package com.cosmin.licenta.workday.service;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.entity.RequiredExperienceReferential;
import com.cosmin.licenta.workday.mapper.RequiredExperienceReferentialMapper;
import com.cosmin.licenta.workday.repository.RequiredExperienceReferentialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequiredExperienceReferentialService {

    private final RequiredExperienceReferentialRepository requiredExperienceReferentialRepository;

    private final RequiredExperienceReferentialMapper requiredExperienceReferentialMapper;

    public RequiredExperienceReferentialService(RequiredExperienceReferentialRepository requiredExperienceReferentialRepository, RequiredExperienceReferentialMapper requiredExperienceReferentialMapper) {
        this.requiredExperienceReferentialRepository = requiredExperienceReferentialRepository;
        this.requiredExperienceReferentialMapper = requiredExperienceReferentialMapper;
    }

    public List<ReferentialDTO> getExperienceReferentials() {
        List<RequiredExperienceReferential> requiredExperienceReferentials = requiredExperienceReferentialRepository.findAll();
        return requiredExperienceReferentialMapper.entitiesToDomains(requiredExperienceReferentials);
    }
}
