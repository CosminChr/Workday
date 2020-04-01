package com.cosmin.licenta.workday.resource;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.service.DayOfWeekReferentialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/dayOfWeekReferential")
public class DayOfWeekReferentialController {

    private final DayOfWeekReferentialService dayOfWeekReferentialService;

    public DayOfWeekReferentialController(DayOfWeekReferentialService dayOfWeekReferentialService) {
        this.dayOfWeekReferentialService = dayOfWeekReferentialService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ReferentialDTO>> getGetMedicalService() {
        return ResponseEntity.ok(dayOfWeekReferentialService.getDayOfWeekReferentials());
    }
}

