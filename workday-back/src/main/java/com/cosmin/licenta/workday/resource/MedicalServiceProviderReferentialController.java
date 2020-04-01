package com.cosmin.licenta.workday.resource;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.service.MedicalServiceProviderReferentialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/medicalServiceProviderReferential")
public class MedicalServiceProviderReferentialController {

    private final MedicalServiceProviderReferentialService medicalServiceProviderReferentialService;

    public MedicalServiceProviderReferentialController(MedicalServiceProviderReferentialService medicalServiceProviderReferentialService) {
        this.medicalServiceProviderReferentialService = medicalServiceProviderReferentialService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ReferentialDTO>> getMedicalServiceRefs() {
        return ResponseEntity.ok(medicalServiceProviderReferentialService.getMedicalServiceProviderReferentials());
    }
}
