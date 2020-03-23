package com.cosmin.licenta.workday.resource;

import com.cosmin.licenta.workday.dto.LocalityReferentialDTO;
import com.cosmin.licenta.workday.service.LocalityReferentialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/localityReferential")
public class LocalityReferentialController {

    private LocalityReferentialService localityReferentialService;

    public LocalityReferentialController(LocalityReferentialService localityReferentialService) {
        this.localityReferentialService = localityReferentialService;
    }

    @GetMapping("/")
    public ResponseEntity<List<LocalityReferentialDTO>> getLocalityRefs() {
        return ResponseEntity.ok(localityReferentialService.getLocalityReferentials());
    }
}
