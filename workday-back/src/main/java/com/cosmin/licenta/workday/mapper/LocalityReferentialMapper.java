package com.cosmin.licenta.workday.mapper;

import com.cosmin.licenta.workday.dto.LocalityReferentialDTO;
import com.cosmin.licenta.workday.entity.LocalityReferential;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CountyReferentialMapper.class, CountryReferentialMapper.class})
public interface LocalityReferentialMapper {

    LocalityReferential domainToEntity(final LocalityReferentialDTO source);

    LocalityReferentialDTO entityToDomain(final LocalityReferential source);

    List<LocalityReferential> domainsToEntities(final List<LocalityReferentialDTO> sourceList);

    List<LocalityReferentialDTO> entitiesToDomains(final List<LocalityReferential> sourceList);
}
