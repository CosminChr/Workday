package com.cosmin.licenta.workday.service;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.entity.StudyLevelReferential;
import com.cosmin.licenta.workday.mapper.StudyLevelReferentialMapper;
import com.cosmin.licenta.workday.repository.StudyLevelReferentialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudyLevelReferentialService {

    private final StudyLevelReferentialRepository studyLevelReferentialRepository;

    private final StudyLevelReferentialMapper studyLevelReferentialMapper;

    public StudyLevelReferentialService(StudyLevelReferentialRepository studyLevelReferentialRepository, StudyLevelReferentialMapper studyLevelReferentialMapper) {
        this.studyLevelReferentialRepository = studyLevelReferentialRepository;
        this.studyLevelReferentialMapper = studyLevelReferentialMapper;
    }

    public List<ReferentialDTO> getStudyLevelReferentials() {
        List<StudyLevelReferential> studyLevelReferentials = studyLevelReferentialRepository.findAll();
        return studyLevelReferentialMapper.entitiesToDomains(studyLevelReferentials);
    }
}
