package com.cosmin.licenta.workday.resource;

import com.cosmin.licenta.workday.dto.MaritalStatusDTO;
import com.cosmin.licenta.workday.service.MaritalStatusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/maritalStatus")
public class MaritalStatusController {

    private final MaritalStatusService maritalStatusService;

    public MaritalStatusController(MaritalStatusService maritalStatusService) {
        this.maritalStatusService = maritalStatusService;
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<MaritalStatusDTO> getMaritalStatus(@PathVariable(name = "employeeId") final Long employeeId) {
        return ResponseEntity.ok(maritalStatusService.getMaritalStatus(employeeId));
    }

    @PutMapping("/")
    public ResponseEntity<MaritalStatusDTO> putMaritalStatus(@RequestBody final MaritalStatusDTO maritalStatusDTO) {
        return ResponseEntity.ok(maritalStatusService.putMaritalStatus(maritalStatusDTO));
    }

}
