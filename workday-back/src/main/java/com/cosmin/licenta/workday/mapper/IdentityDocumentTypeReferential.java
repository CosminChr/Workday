package com.cosmin.licenta.workday.mapper;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IdentityDocumentTypeReferential {

    IdentityDocumentTypeReferential domainToEntity(final ReferentialDTO source);

    ReferentialDTO entityToDomain(final IdentityDocumentTypeReferential source);

    List<IdentityDocumentTypeReferential> domainsToEntities(final List<ReferentialDTO> sourceList);

    List<ReferentialDTO> entitiesToDomains(final List<IdentityDocumentTypeReferential> sourceList);
}
