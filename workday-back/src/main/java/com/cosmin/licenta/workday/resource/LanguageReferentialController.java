package com.cosmin.licenta.workday.resource;

import com.cosmin.licenta.workday.dto.LocalityReferentialDTO;
import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.service.LanguageLevelReferentialService;
import com.cosmin.licenta.workday.service.LanguageReferentialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/languageReferential")
public class LanguageReferentialController {

    private final LanguageReferentialService languageReferentialService;

    public LanguageReferentialController(LanguageReferentialService languageReferentialService) {
        this.languageReferentialService = languageReferentialService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ReferentialDTO>> getLanguageLevelRefs() {
        return ResponseEntity.ok(languageReferentialService.getLanguageRefs());
    }
}
