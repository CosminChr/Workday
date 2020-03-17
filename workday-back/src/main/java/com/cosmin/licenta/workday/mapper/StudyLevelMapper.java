package com.cosmin.licenta.workday.mapper;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.entity.MaritalStatusReferential;
import com.cosmin.licenta.workday.entity.StudyLevelReferential;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudyLevelMapper {

    StudyLevelReferential domainToEntity(final ReferentialDTO source);

    ReferentialDTO entityToDomain(final StudyLevelReferential source);

    List<StudyLevelReferential> domainsToEntities(final List<ReferentialDTO> sourceList);

    List<ReferentialDTO> entitiesToDomains(final List<StudyLevelReferential> sourceList);
}
