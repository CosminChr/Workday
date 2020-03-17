package com.cosmin.licenta.workday.mapper;

import com.cosmin.licenta.workday.dto.IdentityDocumentDTO;
import com.cosmin.licenta.workday.entity.IdentityDocument;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IdentityDocumentMapper {

    IdentityDocument domainToEntity(final IdentityDocumentDTO source);

    IdentityDocumentDTO entityToDomain(final IdentityDocument source);

    List<IdentityDocument> domainsToEntities(final List<IdentityDocumentDTO> sourceList);

    List<IdentityDocumentDTO> entitiesToDomains(final List<IdentityDocument> sourceList);
}
