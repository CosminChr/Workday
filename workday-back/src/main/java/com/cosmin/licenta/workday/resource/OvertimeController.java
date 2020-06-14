package com.cosmin.licenta.workday.resource;

import com.cosmin.licenta.workday.dto.HolidayDTO;
import com.cosmin.licenta.workday.dto.OvertimeDTO;
import com.cosmin.licenta.workday.dto.WorkFromHomeDTO;
import com.cosmin.licenta.workday.service.OvertimeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/overtime")
public class OvertimeController {

    private final OvertimeService overtimeService;

    public OvertimeController(OvertimeService overtimeService) {
        this.overtimeService = overtimeService;
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<List<OvertimeDTO>> getOvertime(@PathVariable(name = "employeeId") final Long employeeId) {
        return ResponseEntity.ok(overtimeService.getOvertimeHistory(employeeId));
    }

    @PutMapping("/")
    public ResponseEntity<OvertimeDTO> putOvertime(@RequestBody final OvertimeDTO overtimeDTO) {
        return ResponseEntity.ok(overtimeService.putOvertime(overtimeDTO));
    }

    @GetMapping("/employees/{managerId}")
    public ResponseEntity<List<OvertimeDTO>> getOvertimeForEmployeesOfManager(@PathVariable(name = "managerId") final Long managerId) {
        return ResponseEntity.ok(overtimeService.getOvertimeHandledByManager(managerId));
    }
}
