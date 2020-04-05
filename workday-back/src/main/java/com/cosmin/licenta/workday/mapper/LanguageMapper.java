package com.cosmin.licenta.workday.mapper;

import com.cosmin.licenta.workday.dto.LanguageDTO;
import com.cosmin.licenta.workday.entity.Language;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {LanguageReferentialMapper.class, LanguageLevelReferentialMapper.class, EmployeeMapper.class})
public interface LanguageMapper {

    Language domainToEntity(final LanguageDTO source);

    LanguageDTO entityToDomain(final Language source);

    List<Language> domainsToEntities(final List<LanguageDTO> sourceList);

    List<LanguageDTO> entitiesToDomains(final List<Language> sourceList);
}
