package com.cosmin.licenta.workday.resource;

import com.cosmin.licenta.workday.dto.response.MenuItemDTO;
import com.cosmin.licenta.workday.dto.response.SubMenuItemDTO;
import com.cosmin.licenta.workday.service.MenuItemService;
import com.cosmin.licenta.workday.service.SubMenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/menu")
public class MenuController {

    @Autowired
    private MenuItemService menuItemService;

    @Autowired
    private SubMenuItemService subMenuItemService;

    @GetMapping("/menuItems")
    public ResponseEntity<List<MenuItemDTO>> getMenuItems() {
        return ResponseEntity.ok(menuItemService.getMenuItems());
    }

    @GetMapping("/subMenuItems")
    public ResponseEntity<List<SubMenuItemDTO>> getSubMenuItems() {
        return ResponseEntity.ok(subMenuItemService.getSubMenuItems());
    }

}
