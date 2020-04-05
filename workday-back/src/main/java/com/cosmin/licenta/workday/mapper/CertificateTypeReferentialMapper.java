package com.cosmin.licenta.workday.mapper;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.entity.CertificateTypeReferential;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CertificateTypeReferentialMapper {

    CertificateTypeReferential domainToEntity(final ReferentialDTO source);

    ReferentialDTO entityToDomain(final CertificateTypeReferential source);

    List<CertificateTypeReferential> domainsToEntities(final List<ReferentialDTO> sourceList);

    List<ReferentialDTO> entitiesToDomains(final List<CertificateTypeReferential> sourceList);
}
