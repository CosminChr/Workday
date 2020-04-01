package com.cosmin.licenta.workday.service;

import com.cosmin.licenta.workday.dto.CompanyJobDTO;
import com.cosmin.licenta.workday.dto.MaritalStatusDTO;
import com.cosmin.licenta.workday.entity.*;
import com.cosmin.licenta.workday.mapper.CompanyJobMapper;
import com.cosmin.licenta.workday.repository.CompanyJobRepository;
import com.cosmin.licenta.workday.repository.ContractTypeReferentialRepository;
import com.cosmin.licenta.workday.repository.EmployeeRepository;
import com.cosmin.licenta.workday.repository.RequiredExperienceReferentialRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyJobService {

    private final CompanyJobRepository companyJobRepository;

    private final CompanyJobMapper companyJobMapper;

    private final EmployeeRepository employeeRepository;

    private final ContractTypeReferentialRepository contractTypeReferentialRepository;

    private final RequiredExperienceReferentialRepository requiredExperienceReferentialRepository;

    public CompanyJobService(CompanyJobRepository companyJobRepository, CompanyJobMapper companyJobMapper, EmployeeRepository employeeRepository, ContractTypeReferentialRepository contractTypeReferentialRepository, RequiredExperienceReferentialRepository requiredExperienceReferentialRepository) {
        this.companyJobRepository = companyJobRepository;
        this.companyJobMapper = companyJobMapper;
        this.employeeRepository = employeeRepository;
        this.contractTypeReferentialRepository = contractTypeReferentialRepository;
        this.requiredExperienceReferentialRepository = requiredExperienceReferentialRepository;
    }

    public List<CompanyJobDTO> getCompanyJobs(final Long employeeId) {
            List<CompanyJob> companyJobs = companyJobRepository.findAll();
            return companyJobMapper.entitiesToDomains(companyJobs);
    }

    public CompanyJobDTO putCompanyJob(final CompanyJobDTO companyJobDTO) {
        Optional<ContractTypeReferential> contractTypeOptional = contractTypeReferentialRepository.findByLabel(companyJobDTO.getContractType().getLabel());
        companyJobDTO.getContractType().setId(contractTypeOptional.get().getId());
        Optional<RequiredExperienceReferential> requiredExperienceOptional = requiredExperienceReferentialRepository.findByLabel(companyJobDTO.getRequiredExperience().getLabel());
        companyJobDTO.getContractType().setId(contractTypeOptional.get().getId());

        companyJobRepository.save(companyJobMapper.domainToEntity(companyJobDTO));
        return companyJobDTO;
    }
}
