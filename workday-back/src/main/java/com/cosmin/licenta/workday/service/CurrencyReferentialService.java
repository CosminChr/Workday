package com.cosmin.licenta.workday.service;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.entity.CurrencyReferential;
import com.cosmin.licenta.workday.mapper.CurrencyReferentialMapper;
import com.cosmin.licenta.workday.repository.CurrencyReferentialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyReferentialService {

    private final CurrencyReferentialRepository currencyReferentialRepository;

    private final CurrencyReferentialMapper currencyReferentialMapper;

    public CurrencyReferentialService(CurrencyReferentialRepository currencyReferentialRepository, CurrencyReferentialMapper currencyReferentialMapper) {
        this.currencyReferentialRepository = currencyReferentialRepository;
        this.currencyReferentialMapper = currencyReferentialMapper;
    }

    public List<ReferentialDTO> getCurrencyReferentials() {
        List<CurrencyReferential> currencyReferentials = currencyReferentialRepository.findAll();
        return currencyReferentialMapper.entitiesToDomains(currencyReferentials);
    }
}
