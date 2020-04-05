package com.cosmin.licenta.workday.mapper;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.entity.MaritalStatusReferential;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MaritalStatusReferentialMapper {

    MaritalStatusReferential domainToEntity(final ReferentialDTO source);

    ReferentialDTO entityToDomain(final MaritalStatusReferential source);

    List<MaritalStatusReferential> domainsToEntities(final List<ReferentialDTO> sourceList);

    List<ReferentialDTO> entitiesToDomains(final List<MaritalStatusReferential> sourceList);
}
