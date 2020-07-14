package com.cosmin.licenta.workday.resource;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.service.JobPositionReferentialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/jobPositionReferential")
public class JobPositionReferentialController {

    private final JobPositionReferentialService jobPositionReferentialService;

    public JobPositionReferentialController(JobPositionReferentialService jobPositionReferentialService) {
        this.jobPositionReferentialService = jobPositionReferentialService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ReferentialDTO>> getJobPositionRefs() {
        return ResponseEntity.ok(jobPositionReferentialService.getJobPositionReferentials());
    }
}
