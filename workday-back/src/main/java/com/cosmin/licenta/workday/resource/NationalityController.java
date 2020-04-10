package com.cosmin.licenta.workday.resource;

import com.cosmin.licenta.workday.dto.AddressDTO;
import com.cosmin.licenta.workday.dto.CitizenshipDTO;
import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.entity.Citizenship;
import com.cosmin.licenta.workday.service.CitizenshipService;
import com.cosmin.licenta.workday.service.NationalityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/nationality")
public class NationalityController {

    private final NationalityService nationalityService;

    public NationalityController(NationalityService nationalityService) {
        this.nationalityService = nationalityService;
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<ReferentialDTO> getNationality(@PathVariable(name = "employeeId") final Long employeeId) {
        return ResponseEntity.ok(nationalityService.getNationality(employeeId));
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<ReferentialDTO> putNationality(@RequestBody final ReferentialDTO nationality, @PathVariable final Long employeeId) {
        return ResponseEntity.ok(nationalityService.putNationality(nationality, employeeId));
    }
}
