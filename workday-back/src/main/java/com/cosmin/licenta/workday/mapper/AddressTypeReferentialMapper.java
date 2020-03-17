package com.cosmin.licenta.workday.mapper;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.entity.AddressTypeReferential;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressTypeReferentialMapper {

    AddressTypeReferential domainToEntity(final ReferentialDTO source);

    ReferentialDTO entityToDomain(final AddressTypeReferential source);

    List<AddressTypeReferential> domainsToEntities(final List<ReferentialDTO> sourceList);

    List<ReferentialDTO> entitiesToDomains(final List<AddressTypeReferential> sourceList);
}
