package com.cosmin.licenta.workday.service;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.entity.CertificateTypeReferential;
import com.cosmin.licenta.workday.entity.ContractTypeReferential;
import com.cosmin.licenta.workday.mapper.CertificateTypeReferentialMapper;
import com.cosmin.licenta.workday.mapper.ContractTypeReferentialMapper;
import com.cosmin.licenta.workday.repository.CertificateTypeReferentialRepository;
import com.cosmin.licenta.workday.repository.ContractTypeReferentialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractTypeReferentialService {

    private final ContractTypeReferentialRepository contractTypeReferentialRepository;

    private final ContractTypeReferentialMapper contractTypeReferentialMapper;

    public ContractTypeReferentialService(ContractTypeReferentialRepository contractTypeReferentialRepository, ContractTypeReferentialMapper contractTypeReferentialMapper) {
        this.contractTypeReferentialRepository = contractTypeReferentialRepository;
        this.contractTypeReferentialMapper = contractTypeReferentialMapper;
    }

    public List<ReferentialDTO> getContractTypeReferentials() {
        List<ContractTypeReferential> contractTypeReferentials = contractTypeReferentialRepository.findAll();
        return contractTypeReferentialMapper.entitiesToDomains(contractTypeReferentials);
    }
}
