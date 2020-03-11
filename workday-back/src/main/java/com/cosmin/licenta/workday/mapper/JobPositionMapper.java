package com.cosmin.licenta.workday.mapper;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.dto.response.SubMenuItemDTO;
import com.cosmin.licenta.workday.entity.JobPositionReferential;
import com.cosmin.licenta.workday.entity.RoleReferential;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface JobPositionMapper {

    JobPositionReferential domainToEntity(final ReferentialDTO source);

    ReferentialDTO entityToDomain(final JobPositionReferential source);

    List<JobPositionReferential> domainsToEntities(final List<ReferentialDTO> sourceList);

    List<ReferentialDTO> entitiesToDomains(final List<JobPositionReferential> sourceList);
}
