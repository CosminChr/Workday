package com.cosmin.licenta.workday.mapper;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.entity.NationalityReferential;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NationalityReferentialMapper {

    NationalityReferential domainToEntity(final ReferentialDTO source);

    ReferentialDTO entityToDomain(final NationalityReferential source);

    List<NationalityReferential> domainsToEntities(final List<ReferentialDTO> sourceList);

    List<ReferentialDTO> entitiesToDomains(final List<NationalityReferential> sourceList);

}
