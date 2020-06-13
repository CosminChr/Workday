package com.cosmin.licenta.workday.resource;

import com.cosmin.licenta.workday.dto.WorkFromHomeDTO;
import com.cosmin.licenta.workday.service.WorkFromHomeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/workFromHome")
public class WorkFromHomeController {

    private final WorkFromHomeService workFromHomeService;

    public WorkFromHomeController(WorkFromHomeService workFromHomeService) {
        this.workFromHomeService = workFromHomeService;
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<WorkFromHomeDTO> getWorkFromHome(@PathVariable(name = "employeeId") final Long employeeId) {
        return ResponseEntity.ok(workFromHomeService.getWorkFromHome(employeeId));
    }

    @PutMapping("/")
    public ResponseEntity<WorkFromHomeDTO> putWorkFromHome(@RequestBody final WorkFromHomeDTO workFromHomeDTO) {
        return ResponseEntity.ok(workFromHomeService.putWorkFromHome(workFromHomeDTO));
    }

    @GetMapping("/employees/{managerId}")
    public ResponseEntity<List<WorkFromHomeDTO>> getWorkFromHomeForEmployeesOfManager(@PathVariable(name = "managerId") final Long managerId) {
        return ResponseEntity.ok(workFromHomeService.getWorkFromHomeForEmployeesOfManager(managerId));
    }
}
