package com.cosmin.licenta.workday.resource;

import com.cosmin.licenta.workday.dto.EmployeeDTO;
import com.cosmin.licenta.workday.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {


    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<EmployeeDTO> getEmployeeByUsername(@PathVariable(name = "username") final String username) {
        return ResponseEntity.ok(employeeService.getEmployee(username));
    }

    @PutMapping("/")
    public ResponseEntity<EmployeeDTO> putEmployee(@RequestBody final EmployeeDTO employee) {
        return ResponseEntity.ok(employeeService.putEmployee(employee));
    }

    @GetMapping("/manager/employees/{managerId}")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByManagerId(@PathVariable(name = "managerId") final Long managerId) {
        return ResponseEntity.ok(employeeService.getEmployeesBymanagerId(managerId));
    }

    @GetMapping("/manager/{managerId}")
    public ResponseEntity<EmployeeDTO> getManager(@PathVariable(name = "managerId") final Long managerId) {
        return ResponseEntity.ok(employeeService.getManager(managerId));
    }

    @GetMapping("/")
    public ResponseEntity<List<EmployeeDTO>> getEmployees() {
        return ResponseEntity.ok(employeeService.getEmployees());
    }
}
