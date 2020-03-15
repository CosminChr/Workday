package com.cosmin.licenta.workday.service;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.entity.HolidayReferential;
import com.cosmin.licenta.workday.mapper.HolidayReferentialMapper;
import com.cosmin.licenta.workday.repository.HolidayReferentialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HolidayReferentialService {

    private HolidayReferentialRepository holidayReferentialRepository;

    private HolidayReferentialMapper holidayReferentialMapper;

    public HolidayReferentialService(HolidayReferentialRepository holidayReferentialRepository, HolidayReferentialMapper holidayReferentialMapper) {
        this.holidayReferentialRepository = holidayReferentialRepository;
        this.holidayReferentialMapper = holidayReferentialMapper;
    }

    public List<ReferentialDTO> getHolidayReferentials() {
        List<HolidayReferential> holidayReferentials = holidayReferentialRepository.findAll();
        return holidayReferentialMapper.entitiesToDomains(holidayReferentials);
    }
}
