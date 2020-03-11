package com.cosmin.licenta.workday.resource;

import com.cosmin.licenta.workday.dto.EmployeeDTO;
import com.cosmin.licenta.workday.dto.response.MenuItemDTO;
import com.cosmin.licenta.workday.dto.response.SubMenuItemDTO;
import com.cosmin.licenta.workday.entity.Employee;
import com.cosmin.licenta.workday.service.EmployeeService;
import com.cosmin.licenta.workday.service.MenuItemService;
import com.cosmin.licenta.workday.service.SubMenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/{username}")
    public ResponseEntity<EmployeeDTO> getEmployeeByUsername(@PathVariable(name = "username") final String username) {
        return ResponseEntity.ok(employeeService.getEmployee(username));
    }

    @PutMapping("/")
    public ResponseEntity<EmployeeDTO> putEmployee(@RequestBody final EmployeeDTO employee) {
        return ResponseEntity.ok(employeeService.putEmployee(employee));
    }


}
