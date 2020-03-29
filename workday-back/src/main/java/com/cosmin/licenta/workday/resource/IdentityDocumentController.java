package com.cosmin.licenta.workday.resource;

import com.cosmin.licenta.workday.dto.IdentityDocumentDTO;
import com.cosmin.licenta.workday.service.IdentityDocumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/identityDocument")
public class IdentityDocumentController {

    private final IdentityDocumentService identityDocumentService;

    public IdentityDocumentController(IdentityDocumentService identityDocumentService) {
        this.identityDocumentService = identityDocumentService;
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<List<IdentityDocumentDTO>> getIdentityDocuments(@PathVariable(name = "employeeId") final Long employeeId) {
        return ResponseEntity.ok(identityDocumentService.getIdentityDocuments(employeeId));
    }

    @PutMapping("/")
    public ResponseEntity<IdentityDocumentDTO> putIdentityDocument(@RequestBody final IdentityDocumentDTO identityDocumentDTO) {
        return ResponseEntity.ok(identityDocumentService.putIdentityDocument(identityDocumentDTO));
    }
}
