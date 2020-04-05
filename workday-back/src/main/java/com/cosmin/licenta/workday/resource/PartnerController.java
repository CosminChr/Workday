package com.cosmin.licenta.workday.resource;

import com.cosmin.licenta.workday.dto.PartnerDTO;
import com.cosmin.licenta.workday.service.PartnerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/partner")
public class PartnerController {

    public PartnerController(PartnerService partnerService) {
        this.partnerService = partnerService;
    }

    private final PartnerService partnerService;

    @GetMapping("/{employeeId}")
    public ResponseEntity<PartnerDTO> getPartner(@PathVariable(name = "employeeId") final Long employeeId) {
        return ResponseEntity.ok(partnerService.getPartner(employeeId));
    }

    @PutMapping("/")
    public ResponseEntity<PartnerDTO> putPartner(@RequestBody final PartnerDTO partnerDTO) {
        return ResponseEntity.ok(partnerService.putPartner(partnerDTO));
    }

}
