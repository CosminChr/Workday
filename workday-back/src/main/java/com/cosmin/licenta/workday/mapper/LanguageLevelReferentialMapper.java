package com.cosmin.licenta.workday.mapper;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.entity.LanguageLevelReferential;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LanguageLevelReferentialMapper {

    LanguageLevelReferential domainToEntity(final ReferentialDTO source);

    ReferentialDTO entityToDomain(final LanguageLevelReferential source);

    List<LanguageLevelReferential> domainsToEntities(final List<ReferentialDTO> sourceList);

    List<ReferentialDTO> entitiesToDomains(final List<LanguageLevelReferential> sourceList);
}
