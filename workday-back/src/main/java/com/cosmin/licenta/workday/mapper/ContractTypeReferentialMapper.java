package com.cosmin.licenta.workday.mapper;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.entity.ContractTypeReferential;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContractTypeReferentialMapper {

    ContractTypeReferential domainToEntity(final ReferentialDTO source);

    ReferentialDTO entityToDomain(final ContractTypeReferential source);

    List<ContractTypeReferential> domainsToEntities(final List<ReferentialDTO> sourceList);

    List<ReferentialDTO> entitiesToDomains(final List<ContractTypeReferential> sourceList);
}
