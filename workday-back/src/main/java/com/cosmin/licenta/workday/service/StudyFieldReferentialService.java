package com.cosmin.licenta.workday.service;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.entity.MaritalStatusReferential;
import com.cosmin.licenta.workday.entity.StudyFieldReferential;
import com.cosmin.licenta.workday.mapper.StudyFieldMapper;
import com.cosmin.licenta.workday.repository.StudyFieldReferentialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudyFieldReferentialService {

    private final StudyFieldReferentialRepository studyFieldReferentialRepository;

    private final StudyFieldMapper studyFieldMapper;

    public StudyFieldReferentialService(StudyFieldReferentialRepository studyFieldReferentialRepository, StudyFieldMapper studyFieldMapper) {
        this.studyFieldReferentialRepository = studyFieldReferentialRepository;
        this.studyFieldMapper = studyFieldMapper;
    }

    public List<ReferentialDTO> getStudyFieldReferentials() {
        List<StudyFieldReferential> studyFieldReferentials = studyFieldReferentialRepository.findAll();
        return studyFieldMapper.entitiesToDomains(studyFieldReferentials);
    }
}
