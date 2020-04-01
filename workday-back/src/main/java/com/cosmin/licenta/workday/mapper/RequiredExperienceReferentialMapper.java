package com.cosmin.licenta.workday.mapper;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.entity.RequiredExperienceReferential;
import com.cosmin.licenta.workday.entity.StudyLevelReferential;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RequiredExperienceReferentialMapper {

    RequiredExperienceReferential domainToEntity(final ReferentialDTO source);

    ReferentialDTO entityToDomain(final RequiredExperienceReferential source);

    List<RequiredExperienceReferential> domainsToEntities(final List<ReferentialDTO> sourceList);

    List<ReferentialDTO> entitiesToDomains(final List<RequiredExperienceReferential> sourceList);
}
