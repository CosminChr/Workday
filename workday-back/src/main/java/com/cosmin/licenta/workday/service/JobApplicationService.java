package com.cosmin.licenta.workday.service;

import com.cosmin.licenta.workday.dto.JobApplicationDTO;
import com.cosmin.licenta.workday.entity.*;
import com.cosmin.licenta.workday.mapper.CompanyJobMapper;
import com.cosmin.licenta.workday.mapper.EmployeeMapper;
import com.cosmin.licenta.workday.mapper.JobApplicationMapper;
import com.cosmin.licenta.workday.repository.*;
import com.cosmin.licenta.workday.service.infrastructure.CompressorService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;

@Service
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;

    private final JobApplicationMapper jobApplicationMapper;

    private final CompanyJobMapper companyJobMapper;

    private final CompanyJobRepository companyJobRepository;

    private final CompressorService compressorService;

    private final EmployeeMapper employeeMapper;

    private final EmployeeRepository employeeRepository;

    public JobApplicationService(JobApplicationRepository jobApplicationRepository, JobApplicationMapper jobApplicationMapper, CompanyJobMapper companyJobMapper, CompanyJobRepository companyJobRepository, CompressorService compressorService, EmployeeMapper employeeMapper, EmployeeRepository employeeRepository) {
        this.jobApplicationRepository = jobApplicationRepository;
        this.jobApplicationMapper = jobApplicationMapper;
        this.companyJobMapper = companyJobMapper;
        this.companyJobRepository = companyJobRepository;
        this.compressorService = compressorService;
        this.employeeMapper = employeeMapper;
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public List<JobApplicationDTO> getJobApplications(final Long employeeId) {

        final Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (employeeOptional.isPresent()) {

            final Optional<List<JobApplication>> jobApplicationsByCompanyJobOptional = jobApplicationRepository.findBySubmittedBy(employeeOptional.get());
            if (jobApplicationsByCompanyJobOptional.isPresent()) {
                return jobApplicationMapper.entitiesToDomains(jobApplicationsByCompanyJobOptional.get());
            }
        }
        return null;
    }

    @Transactional
    public JobApplicationDTO putJobApplication(final JobApplicationDTO jobApplicationDTO, final MultipartFile cv) throws IOException {

        jobApplicationDTO.setCv(cv.getBytes());

        final Optional<CompanyJob> companyJobOptional = companyJobRepository.findById(jobApplicationDTO.getCompanyJob().getId());

        if (companyJobOptional.isPresent()) {
            jobApplicationDTO.setCompanyJob(companyJobMapper.entityToDomain(companyJobOptional.get()));
        }

        final Optional<JobApplication> existingJobApplicationOptional = jobApplicationRepository.findByCompanyJobId(companyJobOptional.get().getId());
        if (existingJobApplicationOptional.isPresent()) {
            jobApplicationDTO.setId(existingJobApplicationOptional.get().getId());
        }

        jobApplicationRepository.save(jobApplicationMapper.domainToEntity(jobApplicationDTO));
        return jobApplicationDTO;
    }
}
