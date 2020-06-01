package com.cosmin.licenta.workday.resource;

import com.cosmin.licenta.workday.dto.CompanyJobDTO;
import com.cosmin.licenta.workday.dto.EmployeeDTO;
import com.cosmin.licenta.workday.dto.JobApplicationDTO;
import com.cosmin.licenta.workday.service.JobApplicationService;
import com.cosmin.licenta.workday.service.infrastructure.CompressorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/jobApplication")
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    public JobApplicationController(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<List<JobApplicationDTO>> getJobApplications(@PathVariable ("employeeId") final Long employeeId) {
        return ResponseEntity.ok(jobApplicationService.getJobApplications(employeeId));
    }

    @PutMapping(value = "/multipart/cv")
    public ResponseEntity<JobApplicationDTO> putJobApplication(@RequestPart (value = "jobApplication") JobApplicationDTO jobApplicationDTO,
                                                               @RequestPart (value="cv") final MultipartFile cv) throws IOException {
         this.jobApplicationService.putJobApplication(jobApplicationDTO, cv);
         return ResponseEntity.ok(jobApplicationDTO);
    }
}
