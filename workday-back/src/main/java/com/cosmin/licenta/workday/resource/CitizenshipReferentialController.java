package com.cosmin.licenta.workday.resource;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.service.CitizenshipReferentialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/citizenshipReferential")
public class CitizenshipReferentialController {

    private final CitizenshipReferentialService citizenshipReferentialService;

    public CitizenshipReferentialController(CitizenshipReferentialService citizenshipReferentialService) {
        this.citizenshipReferentialService = citizenshipReferentialService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ReferentialDTO>> getCitizenshipRefs() {
        return ResponseEntity.ok(citizenshipReferentialService.getCitizenshipReferentials());
    }
}
