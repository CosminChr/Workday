package com.cosmin.licenta.workday.service;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.entity.*;
import com.cosmin.licenta.workday.mapper.*;
import com.cosmin.licenta.workday.repository.*;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;

@Service
public class GenderReferentialService {

    private final GenderReferentialRepository genderReferentialRepository;

    private final GenderReferentialMapper genderReferentialMapper;

    public GenderReferentialService(GenderReferentialRepository genderReferentialRepository, GenderReferentialMapper genderReferentialMapper) {
        this.genderReferentialRepository = genderReferentialRepository;
        this.genderReferentialMapper = genderReferentialMapper;
    }

    public List<ReferentialDTO> getGenderReferentials() {
        List<GenderReferential> genderReferentials = genderReferentialRepository.findAll();
        return genderReferentialMapper.entitiesToDomains(genderReferentials);
    }
}
