package com.cosmin.licenta.workday.service;

import com.cosmin.licenta.workday.dto.response.MenuItemDTO;
import com.cosmin.licenta.workday.mapper.MenuItemMapper;
import com.cosmin.licenta.workday.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService {

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private MenuItemMapper menuItemMapper;

    public List<MenuItemDTO> getMenuItems() {
        return menuItemMapper.entitiesToDomains(menuItemRepository.findAll());
    }
}
