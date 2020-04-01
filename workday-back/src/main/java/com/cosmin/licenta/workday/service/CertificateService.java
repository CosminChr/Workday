package com.cosmin.licenta.workday.service;

import com.cosmin.licenta.workday.dto.CertificateDTO;
import com.cosmin.licenta.workday.entity.CertificateTypeReferential;
import com.cosmin.licenta.workday.mapper.CertificateMapper;
import com.cosmin.licenta.workday.repository.CertificateRepository;
import com.cosmin.licenta.workday.repository.CertificateTypeReferentialRepository;
import com.cosmin.licenta.workday.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CertificateService {

    private final CertificateRepository certificateRepository;

    private final CertificateMapper certificateMapper;

    private final EmployeeRepository employeeRepository;

    private final CertificateTypeReferentialRepository certificateTypeReferentialRepository;

    public CertificateService(CertificateRepository certificateRepository, CertificateMapper certificateMapper, EmployeeRepository employeeRepository, CertificateTypeReferentialRepository certificateTypeReferentialRepository) {
        this.certificateRepository = certificateRepository;
        this.certificateMapper = certificateMapper;
        this.employeeRepository = employeeRepository;
        this.certificateTypeReferentialRepository = certificateTypeReferentialRepository;
    }

    public CertificateDTO putCertificate(final CertificateDTO certificateDTO) {
        Optional<CertificateTypeReferential> certificateTypeOptional = certificateTypeReferentialRepository.findByLabel(certificateDTO.getCertificateType().getLabel());
        certificateDTO.getCertificateType().setId(certificateTypeOptional.get().getId());
        certificateRepository.save(certificateMapper.domainToEntity(certificateDTO));
        return certificateDTO;
    }
}
