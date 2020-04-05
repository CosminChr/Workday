package com.cosmin.licenta.workday.resource;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.service.CertificateTypeReferentialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/certificateTypeReferential")
public class CertificateTypeReferentialController {

    private final CertificateTypeReferentialService certificateTypeReferentialService;

    public CertificateTypeReferentialController(CertificateTypeReferentialService certificateTypeReferentialService) {
        this.certificateTypeReferentialService = certificateTypeReferentialService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ReferentialDTO>> getCertificateTypeRefs() {
        return ResponseEntity.ok(certificateTypeReferentialService.getCertificateTypeReferentials());
    }
}
