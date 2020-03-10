package com.cosmin.licenta.workday.mapper;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.dto.response.SubMenuItemDTO;
import com.cosmin.licenta.workday.entity.RoleReferential;

import java.util.List;

public interface RoleMapper {

    RoleReferential domainToEntity(final ReferentialDTO source);

    SubMenuItemDTO entityToDomain(final RoleReferential source);

    List<RoleReferential> domainsToEntities(final List<ReferentialDTO> sourceList);

    List<ReferentialDTO> entitiesToDomains(final List<RoleReferential> sourceList);
}
