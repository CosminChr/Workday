package com.cosmin.licenta.workday.mapper;

import com.cosmin.licenta.workday.dto.HolidayDTO;
import com.cosmin.licenta.workday.entity.Holiday;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HolidayMapper {

    Holiday domainToEntity(final HolidayDTO source);

    HolidayDTO entityToDomain(final Holiday source);

    List<Holiday> domainsToEntities(final List<HolidayDTO> sourceList);

    List<HolidayDTO> entitiesToDomains(final List<Holiday> sourceList);
}
