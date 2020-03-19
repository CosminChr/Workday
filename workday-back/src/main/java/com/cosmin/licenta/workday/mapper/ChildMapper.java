package com.cosmin.licenta.workday.mapper;

import com.cosmin.licenta.workday.dto.ChildDTO;
import com.cosmin.licenta.workday.entity.Child;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {GenderReferentialMapper.class})
public interface ChildMapper {

    Child domainToEntity(final ChildDTO source);

    ChildDTO entityToDomain(final Child source);

    List<Child> domainsToEntities(final List<ChildDTO> sourceList);

    List<ChildDTO> entitiesToDomains(final List<Child> sourceList);
}
