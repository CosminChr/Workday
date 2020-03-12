package com.cosmin.licenta.workday.mapper;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.entity.HolidayReferential;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HolidayReferentialMapper {

    HolidayReferential domainToEntity(final ReferentialDTO source);

    ReferentialDTO entityToDomain(final HolidayReferential source);

    List<HolidayReferential> domainsToEntities(final List<ReferentialDTO> sourceList);

    List<ReferentialDTO> entitiesToDomains(final List<HolidayReferential> sourceList);
}
