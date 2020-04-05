package com.cosmin.licenta.workday.resource;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.service.IdentityDocumentTypeReferentialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/identityDocumentTypeReferential")
public class IdentityDocumentTypeReferentialController {

    private final IdentityDocumentTypeReferentialService identityDocumentTypeReferentialService;

    public IdentityDocumentTypeReferentialController(IdentityDocumentTypeReferentialService identityDocumentTypeReferentialService) {
        this.identityDocumentTypeReferentialService = identityDocumentTypeReferentialService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ReferentialDTO>> getAddressTypeRefs() {
        return ResponseEntity.ok(identityDocumentTypeReferentialService.getIdentityDocumentTypeReferentials());
    }
}

