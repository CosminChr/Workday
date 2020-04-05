package com.cosmin.licenta.workday.resource;

import com.cosmin.licenta.workday.dto.ChildDTO;
import com.cosmin.licenta.workday.service.ChildService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/child")
public class ChildController {

    private final ChildService childService;

    public ChildController(ChildService childService) {
        this.childService = childService;
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<List<ChildDTO>> getChildren(@PathVariable(name = "employeeId") final Long employeeId) {
        return ResponseEntity.ok(childService.getChildren(employeeId));
    }

    @PutMapping("/")
    public ResponseEntity<ChildDTO> putChild(@RequestBody final ChildDTO childDTO) {
        return ResponseEntity.ok(childService.putChild(childDTO));
    }
}
