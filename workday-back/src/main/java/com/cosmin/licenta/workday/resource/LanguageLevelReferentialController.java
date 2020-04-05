package com.cosmin.licenta.workday.resource;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.service.LanguageLevelReferentialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/languageLevelReferential")
public class LanguageLevelReferentialController {

    private final LanguageLevelReferentialService languageLevelReferentialService;

    public LanguageLevelReferentialController(LanguageLevelReferentialService languageLevelReferentialService) {
        this.languageLevelReferentialService = languageLevelReferentialService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ReferentialDTO>> getLanguageLevelRefs() {
        return ResponseEntity.ok(languageLevelReferentialService.getLanguageLevelRefs());
    }
}
