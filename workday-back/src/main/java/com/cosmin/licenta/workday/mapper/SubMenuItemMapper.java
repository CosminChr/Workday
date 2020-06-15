package com.cosmin.licenta.workday.mapper;

import com.cosmin.licenta.workday.dto.SubMenuItemDTO;
import com.cosmin.licenta.workday.entity.SubMenuItem;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubMenuItemMapper {

    SubMenuItem domainToEntity(final SubMenuItemDTO source);

    SubMenuItemDTO entityToDomain(final SubMenuItem source);

    List<SubMenuItem> domainsToEntities(final List<SubMenuItemDTO> sourceList);

    List<SubMenuItemDTO> entitiesToDomains(final List<SubMenuItem> sourceList);

}
