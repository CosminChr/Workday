package com.cosmin.licenta.workday.resource;

import com.cosmin.licenta.workday.dto.AddressDTO;
import com.cosmin.licenta.workday.dto.LanguageDTO;
import com.cosmin.licenta.workday.service.LanguageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/language")
public class LanguageController {

    private final LanguageService languageService;

    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<List<LanguageDTO>> getLanguages(@PathVariable(name = "employeeId") final Long employeeId) {
        return ResponseEntity.ok(languageService.getLanguages(employeeId));
    }

    @PutMapping("/")
    public ResponseEntity<LanguageDTO> putAddress(@RequestBody final LanguageDTO languageDTO) {
        return ResponseEntity.ok(languageService.putLanguage(languageDTO));
    }
}
