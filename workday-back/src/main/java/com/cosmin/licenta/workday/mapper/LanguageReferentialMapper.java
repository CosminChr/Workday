package com.cosmin.licenta.workday.mapper;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.entity.LanguageReferential;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LanguageReferentialMapper {

    LanguageReferential domainToEntity(final ReferentialDTO source);

    ReferentialDTO entityToDomain(final LanguageReferential source);

    List<LanguageReferential> domainsToEntities(final List<ReferentialDTO> sourceList);

    List<ReferentialDTO> entitiesToDomains(final List<LanguageReferential> sourceList);

}
