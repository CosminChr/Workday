package com.cosmin.licenta.workday.service;

import com.cosmin.licenta.workday.dto.response.SubMenuItemDTO;
import com.cosmin.licenta.workday.mapper.SubMenuItemMapper;
import com.cosmin.licenta.workday.repository.SubMenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubMenuItemService {

    @Autowired
    private SubMenuItemRepository subMenuItemRepository;

    @Autowired
    private SubMenuItemMapper subMenuItemMapper;

    public List<SubMenuItemDTO> getSubMenuItems() {
        return subMenuItemMapper.entitiesToDomains(subMenuItemRepository.findAll());
    }
}
