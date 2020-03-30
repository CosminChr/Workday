package com.cosmin.licenta.workday.mapper;

import com.cosmin.licenta.workday.dto.CitizenshipDTO;
import com.cosmin.licenta.workday.entity.Citizenship;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CitizenshipReferentialMapper.class, EmployeeMapper.class})
public interface CitizenshipMapper {

    Citizenship domainToEntity(final CitizenshipDTO source);

    CitizenshipDTO entityToDomain(final Citizenship source);

    List<Citizenship> domainsToEntities(final List<CitizenshipDTO> sourceList);

    List<CitizenshipDTO> entitiesToDomains(final List<Citizenship> sourceList);
}
