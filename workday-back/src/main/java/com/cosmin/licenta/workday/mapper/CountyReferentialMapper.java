package com.cosmin.licenta.workday.mapper;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.entity.CountyReferential;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountyReferentialMapper {

    CountyReferential domainToEntity(final ReferentialDTO source);

    ReferentialDTO entityToDomain(final CountyReferential source);

    List<CountyReferential> domainsToEntities(final List<ReferentialDTO> sourceList);

    List<ReferentialDTO> entitiesToDomains(final List<CountyReferential> sourceList);
}
