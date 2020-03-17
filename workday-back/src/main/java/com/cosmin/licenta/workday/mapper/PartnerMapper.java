package com.cosmin.licenta.workday.mapper;

import com.cosmin.licenta.workday.dto.PartnerDTO;
import com.cosmin.licenta.workday.entity.Partner;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PartnerMapper {

    Partner domainToEntity(final PartnerDTO source);

    PartnerDTO entityToDomain(final Partner source);

    List<Partner> domainsToEntities(final List<PartnerDTO> sourceList);

    List<PartnerDTO> entitiesToDomains(final List<Partner> sourceList);
}
