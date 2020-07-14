package com.cosmin.licenta.workday.service;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.entity.JobPositionReferential;
import com.cosmin.licenta.workday.mapper.JobPositionReferentialMapper;
import com.cosmin.licenta.workday.repository.JobPositionReferentialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobPositionReferentialService {

    private final JobPositionReferentialRepository jobDomainJobPositionReferentialRepository;

    private final JobPositionReferentialMapper jobDomainReferentialMapper;

    public JobPositionReferentialService(JobPositionReferentialRepository jobDomainJobPositionReferentialRepository, JobPositionReferentialMapper jobDomainReferentialMapper) {
        this.jobDomainJobPositionReferentialRepository = jobDomainJobPositionReferentialRepository;
        this.jobDomainReferentialMapper = jobDomainReferentialMapper;
    }

    public List<ReferentialDTO> getJobPositionReferentials() {
        List<JobPositionReferential> jobPositionReferentials = jobDomainJobPositionReferentialRepository.findAll();
        return jobDomainReferentialMapper.entitiesToDomains(jobPositionReferentials);
    }
}
