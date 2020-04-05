package com.cosmin.licenta.workday.resource;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.service.StudyFieldReferentialService;
import com.cosmin.licenta.workday.service.StudyLevelReferentialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/studyLevelReferential")
public class StudyLevelReferentialController {

    private final StudyLevelReferentialService studyLevelReferentialService;

    public StudyLevelReferentialController(StudyLevelReferentialService studyLevelReferentialService) {
        this.studyLevelReferentialService = studyLevelReferentialService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ReferentialDTO>> getStudyLevelRefs() {
        return ResponseEntity.ok(studyLevelReferentialService.getStudyLevelReferentials());
    }
}
