package com.cosmin.licenta.workday.mapper;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.entity.GenderReferential;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenderReferentialMapper {

    GenderReferential domainToEntity(final ReferentialDTO source);

    ReferentialDTO entityToDomain(final GenderReferential source);

    List<GenderReferential> domainsToEntities(final List<ReferentialDTO> sourceList);

    List<ReferentialDTO> entitiesToDomains(final List<GenderReferential> sourceList);
}
