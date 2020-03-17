package com.cosmin.licenta.workday.mapper;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.entity.DepartmentReferential;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartmentReferentialMapper {

    DepartmentReferential domainToEntity(final ReferentialDTO source);

    ReferentialDTO entityToDomain(final DepartmentReferential source);

    List<DepartmentReferential> domainsToEntities(final List<ReferentialDTO> sourceList);

    List<ReferentialDTO> entitiesToDomains(final List<DepartmentReferential> sourceList);
}
