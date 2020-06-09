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

    private MenuItemService menuItemService;

    private SubMenuItemService subMenuItemService;

    public MenuController(MenuItemService menuItemService, SubMenuItemService subMenuItemService) {
        this.menuItemService = menuItemService;
        this.subMenuItemService = subMenuItemService;
    }

    @GetMapping("/menuItems/{employeeId}")
    public ResponseEntity<List<MenuItemDTO>> getMenuItems(@PathVariable(name = "employeeId") final Long employeeId) {
        return ResponseEntity.ok(menuItemService.getMenuItems(employeeId));
    }

    @GetMapping("/subMenuItems")
    public ResponseEntity<List<SubMenuItemDTO>> getSubMenuItems() {
        return ResponseEntity.ok(subMenuItemService.getSubMenuItems());
    }

}
