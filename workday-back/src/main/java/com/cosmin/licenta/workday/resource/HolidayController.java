package com.cosmin.licenta.workday.resource;

import com.cosmin.licenta.workday.dto.HolidayDTO;
import com.cosmin.licenta.workday.entity.Employee;
import com.cosmin.licenta.workday.service.HolidayService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/holiday")
public class HolidayController {


    private HolidayService holidayService;

    public HolidayController(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<List<HolidayDTO>> getHolidays(@PathVariable(name = "employeeId") final Long employeeId) {
        return ResponseEntity.ok(holidayService.getHolidays(employeeId));
    }

    @PutMapping("/")
    public ResponseEntity<HolidayDTO> putHoliday(@RequestBody final HolidayDTO holidayDTO) {
        return ResponseEntity.ok(holidayService.putHoliday(holidayDTO));
    }

    @GetMapping("/employees/")
    public ResponseEntity<List<HolidayDTO>> getHolidays(@RequestParam(name = "employeeIds") final List<String> employeeIds) {
        return ResponseEntity.ok(holidayService.getHolidays(employeeIds.stream()
                .mapToLong(Long::parseLong).boxed()
                .collect(toList())));
    }

}
