package com.cosmin.licenta.workday.resource;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.service.RequiredExperienceReferentialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/requiredExperienceReferential")
public class RequiredExperienceReferentialController {

    private final RequiredExperienceReferentialService requiredExperienceReferentialService;

    public RequiredExperienceReferentialController(RequiredExperienceReferentialService requiredExperienceReferentialService) {
        this.requiredExperienceReferentialService = requiredExperienceReferentialService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ReferentialDTO>> getRequiredExperienceRefs() {
        return ResponseEntity.ok(requiredExperienceReferentialService.getExperienceReferentials());
    }
}
