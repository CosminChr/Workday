package com.cosmin.licenta.workday.mapper;

import com.cosmin.licenta.workday.dto.response.MenuItemDTO;
import com.cosmin.licenta.workday.entity.MenuItem;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {SubMenuItemMapper.class})
public interface MenuItemMapper {

    MenuItem domainToEntity(final MenuItemDTO source);

    MenuItemDTO entityToDomain(final MenuItem source);

    List<MenuItem> domainsToEntities(final List<MenuItemDTO> sourceList);

    List<MenuItemDTO> entitiesToDomains(final List<MenuItem> sourceList);

}
