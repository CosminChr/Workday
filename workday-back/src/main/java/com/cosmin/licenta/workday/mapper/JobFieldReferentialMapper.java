package com.cosmin.licenta.workday.mapper;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.entity.JobFieldReferential;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface JobFieldReferentialMapper {

    JobFieldReferential domainToEntity(final ReferentialDTO source);

    ReferentialDTO entityToDomain(final JobFieldReferential source);

    List<JobFieldReferential> domainsToEntities(final List<ReferentialDTO> sourceList);

    List<ReferentialDTO> entitiesToDomains(final List<JobFieldReferential> sourceList);
}
