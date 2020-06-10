package com.cosmin.licenta.workday.resource;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.service.RequiredExperienceReferentialService;
import com.cosmin.licenta.workday.service.RoleReferentialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/roleReferential")
public class RoleReferentialController {

    private final RoleReferentialService roleReferentialService;

    public RoleReferentialController(RoleReferentialService roleReferentialService) {
        this.roleReferentialService = roleReferentialService;
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<List<ReferentialDTO>> getRoleRefs(@PathVariable("employeeId") final Long employeeId) {
        return ResponseEntity.ok(roleReferentialService.getRoleReferentials(employeeId));
    }
}
