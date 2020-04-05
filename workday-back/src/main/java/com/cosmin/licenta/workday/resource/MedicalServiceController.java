package com.cosmin.licenta.workday.resource;

import com.cosmin.licenta.workday.dto.MedicalServiceDTO;
import com.cosmin.licenta.workday.service.MedicalServiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/medicalService")
public class MedicalServiceController {

    private final MedicalServiceService medicalServiceService;

    public MedicalServiceController(MedicalServiceService medicalServiceService) {
        this.medicalServiceService = medicalServiceService;
    }


    @GetMapping("/{employeeId}")
    public ResponseEntity<MedicalServiceDTO> getMedicalService(@PathVariable(name = "employeeId") final Long employeeId) {
        return ResponseEntity.ok(medicalServiceService.getMedicalService(employeeId));
    }

    @PutMapping("/")
    public ResponseEntity<MedicalServiceDTO> putMedicalService(@RequestBody final MedicalServiceDTO medicalServiceDTO) {
        return ResponseEntity.ok(medicalServiceService.putMedicalService(medicalServiceDTO));
    }

}
