package com.cosmin.licenta.workday.service;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.entity.HolidayReferential;
import com.cosmin.licenta.workday.entity.JobDomainReferential;
import com.cosmin.licenta.workday.mapper.JobDomainReferentialMapper;
import com.cosmin.licenta.workday.repository.JobDomainReferentialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobDomainReferentialService {

    private final JobDomainReferentialRepository jobDomainReferentialRepository;

    private final JobDomainReferentialMapper jobDomainReferentialMapper;

    public JobDomainReferentialService(JobDomainReferentialRepository jobDomainReferentialRepository, JobDomainReferentialMapper jobDomainReferentialMapper) {
        this.jobDomainReferentialRepository = jobDomainReferentialRepository;
        this.jobDomainReferentialMapper = jobDomainReferentialMapper;
    }

    public List<ReferentialDTO> getJobDomainReferentials() {
        List<JobDomainReferential> jobDomainReferentials = jobDomainReferentialRepository.findAll();
        return jobDomainReferentialMapper.entitiesToDomains(jobDomainReferentials);
    }
}
