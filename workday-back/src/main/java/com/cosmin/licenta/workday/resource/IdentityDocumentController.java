package com.cosmin.licenta.workday.resource;

import com.cosmin.licenta.workday.dto.IdentityDocumentDTO;
import com.cosmin.licenta.workday.service.IdentityDocumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @PutMapping("/multipart/document")
    public ResponseEntity<IdentityDocumentDTO> putIdentityDocument(@RequestPart (value = "identityDocument") final IdentityDocumentDTO identityDocumentDTO,
                                                                   @RequestPart (value = "document") final MultipartFile document) throws IOException {
        identityDocumentService.putIdentityDocument(identityDocumentDTO, document);
        return ResponseEntity.ok(identityDocumentDTO);
    }
}
