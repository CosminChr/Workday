package com.cosmin.licenta.workday.resource;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.service.NationalityReferentialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/nationalityReferential")
public class NationalityReferentialController {

    private final NationalityReferentialService nationalityReferentialService;

    public NationalityReferentialController(NationalityReferentialService nationalityReferentialService) {
        this.nationalityReferentialService = nationalityReferentialService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ReferentialDTO>> getNationaltyRefs() {
        return ResponseEntity.ok(nationalityReferentialService.getNationalityReferentials());
    }
}
