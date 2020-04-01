package com.cosmin.licenta.workday.resource;

import com.cosmin.licenta.workday.dto.CompanyJobDTO;
import com.cosmin.licenta.workday.service.CompanyJobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/companyJob")
public class CompanyJobController {

    private final CompanyJobService companyJobService;

    public CompanyJobController(CompanyJobService companyJobService) {
        this.companyJobService = companyJobService;
    }

    @GetMapping("/")
    public ResponseEntity<List<CompanyJobDTO>> getCompanyJobs() {
        return ResponseEntity.ok(companyJobService.getCompanyJobs());
    }

    @PutMapping("/")
    public ResponseEntity<CompanyJobDTO> putCompanyJob(@RequestBody final CompanyJobDTO companyJobDTO) {
        return ResponseEntity.ok(companyJobService.putCompanyJob(companyJobDTO));
    }
}

