package com.cosmin.licenta.workday.service;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.entity.CertificateTypeReferential;
import com.cosmin.licenta.workday.entity.ContractTypeReferential;
import com.cosmin.licenta.workday.entity.DayOfWeekReferential;
import com.cosmin.licenta.workday.mapper.CertificateTypeReferentialMapper;
import com.cosmin.licenta.workday.mapper.ContractTypeReferentialMapper;
import com.cosmin.licenta.workday.mapper.DayOfWeekReferentialMapper;
import com.cosmin.licenta.workday.repository.CertificateTypeReferentialRepository;
import com.cosmin.licenta.workday.repository.ContractTypeReferentialRepository;
import com.cosmin.licenta.workday.repository.DayOfWeekReferentialRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;

@Service
public class DayOfWeekReferentialService {

    private final DayOfWeekReferentialRepository dayOfWeekReferentialRepository;

    private final DayOfWeekReferentialMapper dayOfWeekReferentialMapper;

    public DayOfWeekReferentialService(DayOfWeekReferentialRepository dayOfWeekReferentialRepository, DayOfWeekReferentialMapper dayOfWeekReferentialMapper) {
        this.dayOfWeekReferentialRepository = dayOfWeekReferentialRepository;
        this.dayOfWeekReferentialMapper = dayOfWeekReferentialMapper;
    }

    public List<ReferentialDTO> getDayOfWeekReferentials() {
        List<DayOfWeekReferential> dayOfWeekReferentials = dayOfWeekReferentialRepository.findAll();
        return dayOfWeekReferentialMapper.entitiesToDomains(dayOfWeekReferentials);
    }
}
