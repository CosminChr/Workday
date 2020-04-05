package com.cosmin.licenta.workday.service;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.entity.CertificateTypeReferential;
import com.cosmin.licenta.workday.entity.ContractTypeReferential;
import com.cosmin.licenta.workday.entity.DayOfWeekReferential;
import com.cosmin.licenta.workday.entity.MedicalServiceProviderReferential;
import com.cosmin.licenta.workday.mapper.CertificateTypeReferentialMapper;
import com.cosmin.licenta.workday.mapper.ContractTypeReferentialMapper;
import com.cosmin.licenta.workday.mapper.DayOfWeekReferentialMapper;
import com.cosmin.licenta.workday.mapper.MedicalServiceProviderReferentialMapper;
import com.cosmin.licenta.workday.repository.CertificateTypeReferentialRepository;
import com.cosmin.licenta.workday.repository.ContractTypeReferentialRepository;
import com.cosmin.licenta.workday.repository.DayOfWeekReferentialRepository;
import com.cosmin.licenta.workday.repository.MedicalServiceProviderReferentialRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;

@Service
public class MedicalServiceProviderReferentialService {

    private final MedicalServiceProviderReferentialRepository medicalServiceProviderReferentialRepository;

    private final MedicalServiceProviderReferentialMapper medicalServiceProviderReferentialMapper;

    public MedicalServiceProviderReferentialService(MedicalServiceProviderReferentialRepository medicalServiceProviderReferentialRepository, MedicalServiceProviderReferentialMapper medicalServiceProviderReferentialMapper) {
        this.medicalServiceProviderReferentialRepository = medicalServiceProviderReferentialRepository;
        this.medicalServiceProviderReferentialMapper = medicalServiceProviderReferentialMapper;
    }

    public List<ReferentialDTO> getMedicalServiceProviderReferentials() {
        List<MedicalServiceProviderReferential> medicalServiceProviderReferentials = medicalServiceProviderReferentialRepository.findAll();
        return medicalServiceProviderReferentialMapper.entitiesToDomains(medicalServiceProviderReferentials);
    }
}
