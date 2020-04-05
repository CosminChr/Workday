package com.cosmin.licenta.workday.service;

import com.cosmin.licenta.workday.dto.PreviousJobDTO;
import com.cosmin.licenta.workday.entity.*;
import com.cosmin.licenta.workday.mapper.LocalityReferentialMapper;
import com.cosmin.licenta.workday.mapper.PreviousJobMapper;
import com.cosmin.licenta.workday.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PreviousJobService {

    private final EmployeeRepository employeeRepository;

    private final PreviousJobRepository previousJobRepository;

    private final PreviousJobMapper previousJobMapper;

    private final JobDomainReferentialRepository jobDomainReferentialRepository;

    private final LocalityReferentialRepository localityReferentialRepository;

    private final CountyReferentialRepository countyReferentialRepository;

    private final CountryReferentialRepository countryReferentialRepository;

    private final LocalityReferentialMapper localityReferentialMapper;

    public PreviousJobService(EmployeeRepository employeeRepository, PreviousJobRepository previousJobRepository, PreviousJobMapper previousJobMapper, JobDomainReferentialRepository jobDomainReferentialRepository, LocalityReferentialRepository localityReferentialRepository, CountyReferentialRepository countyReferentialRepository, CountryReferentialRepository countryReferentialRepository, LocalityReferentialMapper localityReferentialMapper) {
        this.employeeRepository = employeeRepository;
        this.previousJobRepository = previousJobRepository;
        this.previousJobMapper = previousJobMapper;
        this.jobDomainReferentialRepository = jobDomainReferentialRepository;
        this.localityReferentialRepository = localityReferentialRepository;
        this.countyReferentialRepository = countyReferentialRepository;
        this.countryReferentialRepository = countryReferentialRepository;
        this.localityReferentialMapper = localityReferentialMapper;
    }

    public List<PreviousJobDTO> getPreviousJobs(final Long employeeId) {

        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (employeeOptional.isPresent()) {
            Optional<List<PreviousJob>> previousJobsOptionalList = this.previousJobRepository.findByEmployee(employeeOptional.get());
            if (previousJobsOptionalList.isPresent()) {
                return previousJobMapper.entitiesToDomains(previousJobsOptionalList.get());
            }
        }
        return null;
    }

    public PreviousJobDTO putPreviousJob(final PreviousJobDTO previousJobDTO) {
        Optional<JobDomainReferential> jobDomainOptional = jobDomainReferentialRepository.findByLabel(previousJobDTO.getJobDomain().getLabel());
        previousJobDTO.getJobDomain().setId(jobDomainOptional.get().getId());

        Optional<CountyReferential> countyOptional = countyReferentialRepository.findByLabel(previousJobDTO.getLocality().getCounty().getLabel());
        Optional<CountryReferential> countryOptional = countryReferentialRepository.findByLabel(previousJobDTO.getLocality().getCountry().getLabel());
        Optional<LocalityReferential> localityReferentialOptional = localityReferentialRepository.findByLabelAndCountyAndCountry(previousJobDTO.getLocality().getLabel(), countyOptional.get(), countryOptional.get());

        previousJobDTO.setLocality(localityReferentialMapper.entityToDomain(localityReferentialOptional.get()));
        previousJobRepository.save(previousJobMapper.domainToEntity(previousJobDTO));
        return previousJobDTO;
    }
}
