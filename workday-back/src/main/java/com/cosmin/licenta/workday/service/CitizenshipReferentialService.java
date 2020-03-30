package com.cosmin.licenta.workday.service;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.entity.CitizenshipReferential;
import com.cosmin.licenta.workday.mapper.CitizenshipReferentialMapper;
import com.cosmin.licenta.workday.repository.CitizenshipReferentialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitizenshipReferentialService {

    private final CitizenshipReferentialRepository citizenshipReferentialRepository;

    private final CitizenshipReferentialMapper citizenshipReferentialMapper;

    public CitizenshipReferentialService(CitizenshipReferentialRepository citizenshipReferentialRepository, CitizenshipReferentialMapper citizenshipReferentialMapper) {
        this.citizenshipReferentialRepository = citizenshipReferentialRepository;
        this.citizenshipReferentialMapper = citizenshipReferentialMapper;
    }


    public List<ReferentialDTO> getCitizenshipReferentials() {
        List<CitizenshipReferential> citizenshipReferentials = citizenshipReferentialRepository.findAll();
        return citizenshipReferentialMapper.entitiesToDomains(citizenshipReferentials);
    }
}
