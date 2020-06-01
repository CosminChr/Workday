package com.cosmin.licenta.workday.resource;

import com.cosmin.licenta.workday.dto.AddressDTO;
import com.cosmin.licenta.workday.dto.CitizenshipDTO;
import com.cosmin.licenta.workday.dto.LanguageDTO;
import com.cosmin.licenta.workday.service.LanguageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @PutMapping("/multipart/languageCertification")
    public ResponseEntity<LanguageDTO> putAddress(@RequestPart(value = "language") final LanguageDTO languageDTO,
                                                  @RequestPart(value = "languageCertification") final MultipartFile document) throws IOException {
        return ResponseEntity.ok(languageService.putLanguage(languageDTO, document));
    }
}
