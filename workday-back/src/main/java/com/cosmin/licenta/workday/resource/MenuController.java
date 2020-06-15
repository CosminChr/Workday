package com.cosmin.licenta.workday.resource;

import com.cosmin.licenta.workday.dto.MenuItemDTO;
import com.cosmin.licenta.workday.dto.SubMenuItemDTO;
import com.cosmin.licenta.workday.service.MenuItemService;
import com.cosmin.licenta.workday.service.SubMenuItemService;
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

    @GetMapping("/menuItems/employee/{employeeId}")
    public ResponseEntity<List<MenuItemDTO>> getMenuItemsForEmployee(@PathVariable(name = "employeeId") final Long employeeId) {
        return ResponseEntity.ok(menuItemService.getMenuItemsForEmployee(employeeId));
    }

    @GetMapping("/menuItems/admin/{adminId}")
    public ResponseEntity<List<MenuItemDTO>> getMenuItemsForAdmin(@PathVariable(name = "adminId") final Long adminId) {
        return ResponseEntity.ok(menuItemService.getMenuItemsForAdmin(adminId));
    }

    @GetMapping("/subMenuItems")
    public ResponseEntity<List<SubMenuItemDTO>> getSubMenuItems() {
        return ResponseEntity.ok(subMenuItemService.getSubMenuItems());
    }

}
