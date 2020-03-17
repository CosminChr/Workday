package com.cosmin.licenta.workday.mapper;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.entity.CountryReferential;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryReferentialMapper {

    CountryReferential domainToEntity(final ReferentialDTO source);

    ReferentialDTO entityToDomain(final CountryReferential source);

    List<CountryReferential> domainsToEntities(final List<ReferentialDTO> sourceList);

    List<ReferentialDTO> entitiesToDomains(final List<CountryReferential> sourceList);
}
