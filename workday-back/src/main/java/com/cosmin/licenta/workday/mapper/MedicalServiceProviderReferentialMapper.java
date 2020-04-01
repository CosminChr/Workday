package com.cosmin.licenta.workday.mapper;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.entity.MedicalServiceProviderReferential;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MedicalServiceProviderReferentialMapper {

    MedicalServiceProviderReferential domainToEntity(final ReferentialDTO source);

    ReferentialDTO entityToDomain(MedicalServiceProviderReferential source);

    List<MedicalServiceProviderReferential> domainsToEntities(final List<ReferentialDTO> sourceList);

    List<ReferentialDTO> entitiesToDomains(final List<MedicalServiceProviderReferential> sourceList);

}
