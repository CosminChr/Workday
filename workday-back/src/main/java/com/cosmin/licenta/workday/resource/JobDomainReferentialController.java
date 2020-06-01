package com.cosmin.licenta.workday.resource;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.service.JobDomainReferentialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/jobDomainReferential")
public class JobDomainReferentialController {

    private final JobDomainReferentialService jobDomainReferentialService;

    public JobDomainReferentialController(JobDomainReferentialService jobDomainReferentialService) {
        this.jobDomainReferentialService = jobDomainReferentialService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ReferentialDTO>> getLocalityRefs() {
        return ResponseEntity.ok(jobDomainReferentialService.getJobDomainReferentials());
    }
}
