package com.cosmin.licenta.workday.mapper;

import com.cosmin.licenta.workday.dto.JobApplicationDTO;
import com.cosmin.licenta.workday.entity.CompanyJob;
import com.cosmin.licenta.workday.entity.JobApplication;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",  uses = {EmployeeMapper.class,
        CompanyJobMapper.class})
public interface JobApplicationMapper {

    JobApplication domainToEntity(final JobApplicationDTO source);

    JobApplicationDTO entityToDomain(final JobApplication source);

    List<JobApplication> domainsToEntities(final List<JobApplicationDTO> sourceList);

    List<JobApplicationDTO> entitiesToDomains(final List<JobApplication> sourceList);
}
