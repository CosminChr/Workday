package com.cosmin.licenta.workday.service;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.entity.JobFieldReferential;
import com.cosmin.licenta.workday.mapper.JobFieldReferentialMapper;
import com.cosmin.licenta.workday.repository.JobFieldRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobFieldReferentialService {

    private final JobFieldRepository jobFieldRepository;

    private final JobFieldReferentialMapper jobFieldReferentialMapper;

    public JobFieldReferentialService(JobFieldRepository jobFieldRepository, JobFieldReferentialMapper jobFieldReferentialMapper) {
        this.jobFieldRepository = jobFieldRepository;
        this.jobFieldReferentialMapper = jobFieldReferentialMapper;
    }

    public List<ReferentialDTO> getJobFieldReferentials() {
        List<JobFieldReferential> jobFieldReferentials = jobFieldRepository.findAll();
        return jobFieldReferentialMapper.entitiesToDomains(jobFieldReferentials);
    }
}
