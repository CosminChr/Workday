package com.cosmin.licenta.workday.resource;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.service.CountryReferentialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/countryReferential")
public class CountryReferentialController {

    private final CountryReferentialService countryReferentialService;

    public CountryReferentialController(CountryReferentialService countryReferentialService) {
        this.countryReferentialService = countryReferentialService;
    }


    @GetMapping("/")
    public ResponseEntity<List<ReferentialDTO>> getCountryRefs() {
        return ResponseEntity.ok(countryReferentialService.getCountryReferentials());
    }
}
