package com.cosmin.licenta.workday.mapper;

import com.cosmin.licenta.workday.dto.WorkFromHomeDTO;
import com.cosmin.licenta.workday.entity.DayOfWeekReferential;
import com.cosmin.licenta.workday.entity.WorkFromHome;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DayOfWeekReferentialMapper.class})
public interface WorkFromHomeMapper {

    WorkFromHome domainToEntity(final WorkFromHomeDTO source);

    WorkFromHomeDTO entityToDomain(final WorkFromHome source);

    List<WorkFromHome> domainsToEntities(final List<WorkFromHomeDTO> sourceList);

    List<WorkFromHomeDTO> entitiesToDomains(final List<WorkFromHome> sourceList);

}
