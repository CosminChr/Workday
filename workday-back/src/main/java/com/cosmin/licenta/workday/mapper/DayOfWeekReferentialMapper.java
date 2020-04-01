package com.cosmin.licenta.workday.mapper;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.entity.DayOfWeekReferential;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DayOfWeekReferentialMapper {

    DayOfWeekReferential domainToEntity(final ReferentialDTO source);

    ReferentialDTO entityToDomain(final DayOfWeekReferential source);

    List<DayOfWeekReferential> domainsToEntities(final List<ReferentialDTO> sourceList);

    List<ReferentialDTO> entitiesToDomains(final List<DayOfWeekReferential> sourceList);
}
