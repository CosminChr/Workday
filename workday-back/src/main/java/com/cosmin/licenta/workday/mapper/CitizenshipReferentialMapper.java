package com.cosmin.licenta.workday.mapper;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.entity.AddressTypeReferential;
import com.cosmin.licenta.workday.entity.Citizenship;
import com.cosmin.licenta.workday.entity.CitizenshipReferential;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CitizenshipReferentialMapper {

    CitizenshipReferential domainToEntity(final ReferentialDTO source);

    ReferentialDTO entityToDomain(final CitizenshipReferential source);

    List<CitizenshipReferential> domainsToEntities(final List<ReferentialDTO> sourceList);

    List<ReferentialDTO> entitiesToDomains(final List<CitizenshipReferential> sourceList);
}
