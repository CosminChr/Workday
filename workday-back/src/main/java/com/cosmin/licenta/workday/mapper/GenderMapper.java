package com.cosmin.licenta.workday.mapper;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.dto.response.SubMenuItemDTO;
import com.cosmin.licenta.workday.entity.GenderReferential;
import com.cosmin.licenta.workday.entity.JobPositionReferential;

import java.util.List;

public interface GenderMapper {

    GenderReferential domainToEntity(final ReferentialDTO source);

    SubMenuItemDTO entityToDomain(final GenderReferential source);

    List<GenderReferential> domainsToEntities(final List<ReferentialDTO> sourceList);

    List<ReferentialDTO> entitiesToDomains(final List<GenderReferential> sourceList);
}
