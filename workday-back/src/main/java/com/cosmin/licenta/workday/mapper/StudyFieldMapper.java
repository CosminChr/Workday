package com.cosmin.licenta.workday.mapper;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.entity.MaritalStatusReferential;
import com.cosmin.licenta.workday.entity.StudyFieldReferential;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudyFieldMapper {

    StudyFieldReferential domainToEntity(final ReferentialDTO source);

    ReferentialDTO entityToDomain(final StudyFieldReferential source);

    List<StudyFieldReferential> domainsToEntities(final List<ReferentialDTO> sourceList);

    List<ReferentialDTO> entitiesToDomains(final List<StudyFieldReferential> sourceList);
}
