package com.cosmin.licenta.workday.resource;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.service.GenderReferentialService;
import com.cosmin.licenta.workday.service.HolidayReferentialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/genderReferential")
public class GenderReferentialController {

    private GenderReferentialService genderReferentialService;

    public GenderReferentialController(GenderReferentialService genderReferentialService) {
        this.genderReferentialService = genderReferentialService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ReferentialDTO>> getGenderRefs() {
        return ResponseEntity.ok(genderReferentialService.getGenderReferentials());
    }
}


