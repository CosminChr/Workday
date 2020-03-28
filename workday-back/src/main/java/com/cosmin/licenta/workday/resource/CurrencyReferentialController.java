package com.cosmin.licenta.workday.resource;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.service.CurrencyReferentialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/currencyReferential")
public class CurrencyReferentialController {

    private final CurrencyReferentialService currencyReferentialService;

    public CurrencyReferentialController(CurrencyReferentialService currencyReferentialService) {
        this.currencyReferentialService = currencyReferentialService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ReferentialDTO>> getCurrencyRefs() {
        return ResponseEntity.ok(currencyReferentialService.getCurrencyReferentials());
    }
}
