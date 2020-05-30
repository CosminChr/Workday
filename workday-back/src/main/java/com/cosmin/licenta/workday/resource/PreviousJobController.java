package com.cosmin.licenta.workday.resource;

import com.cosmin.licenta.workday.dto.HolidayDTO;
import com.cosmin.licenta.workday.dto.PreviousJobDTO;
import com.cosmin.licenta.workday.entity.PreviousJob;
import com.cosmin.licenta.workday.service.HolidayService;
import com.cosmin.licenta.workday.service.PreviousJobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/previousJob")
public class PreviousJobController {


    private final PreviousJobService previousJobService;

    public PreviousJobController(PreviousJobService previousJobService) {
        this.previousJobService = previousJobService;
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<List<PreviousJobDTO>> getPreviousJobs(@PathVariable(name = "employeeId") final Long employeeId) {
        return ResponseEntity.ok(previousJobService.getPreviousJobs(employeeId));
    }

    @PutMapping("/")
    public ResponseEntity<PreviousJobDTO> putPreviousJob(@RequestBody final PreviousJobDTO previousJobDTO) {
        return ResponseEntity.ok(previousJobService.putPreviousJob(previousJobDTO));
    }

}
