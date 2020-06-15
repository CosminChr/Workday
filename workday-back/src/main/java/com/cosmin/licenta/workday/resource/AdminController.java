package com.cosmin.licenta.workday.resource;

import com.cosmin.licenta.workday.dto.AdminDTO;
import com.cosmin.licenta.workday.dto.EmployeeDTO;
import com.cosmin.licenta.workday.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    @GetMapping("/{username}")
    public ResponseEntity<AdminDTO> getEmployeeByUsername(@PathVariable(name = "username") final String username) {
        return ResponseEntity.ok(adminService.getAdmin(username));
    }
}
