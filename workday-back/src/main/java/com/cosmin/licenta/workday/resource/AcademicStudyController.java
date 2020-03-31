package com.cosmin.licenta.workday.resource;

import com.cosmin.licenta.workday.dto.AcademicStudyDTO;
import com.cosmin.licenta.workday.service.AcademicStudyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/academicStudy")
public class AcademicStudyController {

    private final AcademicStudyService academicStudyService;

    public AcademicStudyController(AcademicStudyService academicStudyService) {
        this.academicStudyService = academicStudyService;
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<List<AcademicStudyDTO>> getAcademicStudies(@PathVariable(name = "employeeId") final Long employeeId) {
        return ResponseEntity.ok(academicStudyService.getAcademicStudies(employeeId));
    }

    @PutMapping("/")
    public ResponseEntity<AcademicStudyDTO> putAcademicStudy(@RequestBody final AcademicStudyDTO academicStudyDTO) {
        return ResponseEntity.ok(academicStudyService.putAcademicStudy(academicStudyDTO));
    }
}
