package com.cosmin.licenta.workday.resource;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.service.HolidayReferentialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/holidayReferential")
public class HolidayReferentialController {

    private HolidayReferentialService holidayReferentialService;

    public HolidayReferentialController(HolidayReferentialService holidayReferentialService) {
        this.holidayReferentialService = holidayReferentialService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ReferentialDTO>> getHolidays() {
        return ResponseEntity.ok(holidayReferentialService.getHolidayReferentials());
    }
}


