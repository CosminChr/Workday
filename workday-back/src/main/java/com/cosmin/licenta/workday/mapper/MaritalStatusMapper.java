package com.cosmin.licenta.workday.mapper;

import com.cosmin.licenta.workday.dto.MaritalStatusDTO;
import com.cosmin.licenta.workday.entity.MaritalStatus;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {MaritalStatusReferentialMapper.class, PartnerMapper.class})
public interface MaritalStatusMapper {

    MaritalStatus domainToEntity(final MaritalStatusDTO source);

    MaritalStatusDTO entityToDomain(final MaritalStatus source);

    List<MaritalStatus> domainsToEntities(final List<MaritalStatusDTO> sourceList);

    List<MaritalStatusDTO> entitiesToDomains(final List<MaritalStatus> sourceList);
}
