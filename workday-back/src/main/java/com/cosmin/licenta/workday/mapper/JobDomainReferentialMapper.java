package com.cosmin.licenta.workday.mapper;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.entity.JobDomainReferential;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface JobDomainReferentialMapper {

    JobDomainReferential domainToEntity(final ReferentialDTO source);

    ReferentialDTO entityToDomain(final JobDomainReferential source);

    List<JobDomainReferential> domainsToEntities(final List<ReferentialDTO> sourceList);

    List<ReferentialDTO> entitiesToDomains(final List<JobDomainReferential> sourceList);
}
