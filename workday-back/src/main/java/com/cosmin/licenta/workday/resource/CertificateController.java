package com.cosmin.licenta.workday.resource;

import com.cosmin.licenta.workday.dto.CertificateDTO;
import com.cosmin.licenta.workday.service.CertificateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/certificate")
public class CertificateController {

    private final CertificateService certificateService;

    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @PutMapping("/")
    public ResponseEntity<CertificateDTO> putCertificate(@RequestBody final CertificateDTO certificateDTO) {
        return ResponseEntity.ok(certificateService.putCertificate(certificateDTO));
    }
}
