package com.cosmin.licenta.workday.mapper;

import com.cosmin.licenta.workday.dto.OvertimeDTO;
import com.cosmin.licenta.workday.entity.Overtime;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {EmployeeMapper.class})
public interface OvertimeMapper {

    Overtime domainToEntity(final OvertimeDTO source);

    OvertimeDTO entityToDomain(final Overtime source);

    List<Overtime> domainsToEntities(final List<OvertimeDTO> sourceList);

    List<OvertimeDTO> entitiesToDomains(final List<Overtime> sourceList);

}
