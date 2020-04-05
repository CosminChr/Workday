package com.cosmin.licenta.workday.resource;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.service.StudyFieldReferentialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/studyFieldReferential")
public class StudyFieldReferentialController {

    private final StudyFieldReferentialService studyFieldReferentialService;

    public StudyFieldReferentialController(StudyFieldReferentialService studyFieldReferentialService) {
        this.studyFieldReferentialService = studyFieldReferentialService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ReferentialDTO>> getStudyFieldRefs() {
        return ResponseEntity.ok(studyFieldReferentialService.getStudyFieldReferentials());
    }
}
