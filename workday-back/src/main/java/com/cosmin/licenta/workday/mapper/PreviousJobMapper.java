package com.cosmin.licenta.workday.mapper;

import com.cosmin.licenta.workday.dto.PreviousJobDTO;
import com.cosmin.licenta.workday.entity.PreviousJob;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {LocalityReferentialMapper.class, EmployeeMapper.class, LocalityReferentialMapper.class})
public interface PreviousJobMapper {

    PreviousJob domainToEntity(final PreviousJobDTO source);

    PreviousJobDTO entityToDomain(final PreviousJob source);

    List<PreviousJob> domainsToEntities(final List<PreviousJobDTO> sourceList);

    List<PreviousJobDTO> entitiesToDomains(final List<PreviousJob> sourceList);
}
