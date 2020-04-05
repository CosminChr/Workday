package com.cosmin.licenta.workday.resource;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.service.DepartmentReferentialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/departmentReferential")
public class DepartmentReferentialController {

    private final DepartmentReferentialService departmentReferentialService;

    public DepartmentReferentialController(DepartmentReferentialService departmentReferentialService) {
        this.departmentReferentialService = departmentReferentialService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ReferentialDTO>> getDepartmentRferential() {
        return ResponseEntity.ok(departmentReferentialService.getDepartmentReferentials());
    }
}
