package com.cosmin.licenta.workday.resource;

import com.cosmin.licenta.workday.dto.CitizenshipDTO;
import com.cosmin.licenta.workday.service.CitizenshipService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/")
    public ResponseEntity<CitizenshipDTO> putCitizenship(@RequestBody final CitizenshipDTO citizenshipDTO) {
        return ResponseEntity.ok(citizenshipService.putCitizenship(citizenshipDTO));
    }
}
