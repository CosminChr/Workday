package com.cosmin.licenta.workday.resource;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.service.JobFieldReferentialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/jobFieldReferential")
public class JobFieldReferentialController {

    private final JobFieldReferentialService jobFieldReferentialService;

    public JobFieldReferentialController(JobFieldReferentialService jobFieldReferentialService) {
        this.jobFieldReferentialService = jobFieldReferentialService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ReferentialDTO>> getLocalityRefs() {
        return ResponseEntity.ok(jobFieldReferentialService.getJobFieldReferentials());
    }
}
