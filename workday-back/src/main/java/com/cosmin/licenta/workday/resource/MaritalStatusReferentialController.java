package com.cosmin.licenta.workday.resource;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.service.MaritalStatusReferentialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/maritalStatusReferential")
public class MaritalStatusReferentialController {

    private final MaritalStatusReferentialService maritalStatusReferentialService;

    public MaritalStatusReferentialController(MaritalStatusReferentialService maritalStatusReferentialService) {
        this.maritalStatusReferentialService = maritalStatusReferentialService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ReferentialDTO>> getMaritalStatusRefs() {
        return ResponseEntity.ok(maritalStatusReferentialService.getMaritalStatusReferentials());
    }
}
