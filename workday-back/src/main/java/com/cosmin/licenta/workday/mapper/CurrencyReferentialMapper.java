package com.cosmin.licenta.workday.mapper;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.entity.CurrencyReferential;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CurrencyReferentialMapper {

    CurrencyReferential domainToEntity(final ReferentialDTO source);

    ReferentialDTO entityToDomain(final CurrencyReferential source);

    List<CurrencyReferential> domainsToEntities(final List<ReferentialDTO> sourceList);

    List<ReferentialDTO> entitiesToDomains(final List<CurrencyReferential> sourceList);
}
