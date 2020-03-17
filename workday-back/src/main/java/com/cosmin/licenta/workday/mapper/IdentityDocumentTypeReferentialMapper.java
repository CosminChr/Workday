package com.cosmin.licenta.workday.mapper;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.entity.IdentityDocumentTypeReferential;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IdentityDocumentTypeReferentialMapper {

    IdentityDocumentTypeReferential domainToEntity(final ReferentialDTO source);

    ReferentialDTO entityToDomain(final IdentityDocumentTypeReferential source);

    List<IdentityDocumentTypeReferential> domainsToEntities(final List<ReferentialDTO> sourceList);

    List<ReferentialDTO> entitiesToDomains(final List<IdentityDocumentTypeReferential> sourceList);
}
