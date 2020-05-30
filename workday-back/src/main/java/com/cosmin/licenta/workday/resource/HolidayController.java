package com.cosmin.licenta.workday.resource;

import com.cosmin.licenta.workday.dto.HolidayDTO;
import com.cosmin.licenta.workday.service.HolidayService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

}
