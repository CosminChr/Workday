package com.cosmin.licenta.workday.resource;

import com.cosmin.licenta.workday.dto.CitizenshipDTO;
import com.cosmin.licenta.workday.dto.MaritalStatusDTO;
import com.cosmin.licenta.workday.service.CitizenshipService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/citizenship")
public class CitizenshipController {

    private final CitizenshipService citizenshipService;

    public CitizenshipController(CitizenshipService citizenshipService) {
        this.citizenshipService = citizenshipService;
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<List<CitizenshipDTO>> getCitizenships(@PathVariable(name = "employeeId") final Long employeeId) {
        return ResponseEntity.ok(citizenshipService.getCitizenships(employeeId));
    }

    @PutMapping("/multipart/citizenshipCertificate")
    public ResponseEntity<CitizenshipDTO> putCitizenship(@RequestPart(value = "citizenship") final CitizenshipDTO citizenshipDTO,
                                                         @RequestPart(value = "citizenshipCertificate") final MultipartFile document) throws IOException {
        return ResponseEntity.ok(citizenshipService.putCitizenship(citizenshipDTO, document));
    }
}
