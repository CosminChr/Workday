package com.cosmin.licenta.workday.service;

import com.cosmin.licenta.workday.dto.LanguageDTO;
import com.cosmin.licenta.workday.entity.Employee;
import com.cosmin.licenta.workday.entity.Language;
import com.cosmin.licenta.workday.entity.LanguageLevelReferential;
import com.cosmin.licenta.workday.entity.LanguageReferential;
import com.cosmin.licenta.workday.mapper.LanguageMapper;
import com.cosmin.licenta.workday.repository.EmployeeRepository;
import com.cosmin.licenta.workday.repository.LanguageLevelReferentialRepository;
import com.cosmin.licenta.workday.repository.LanguageReferentialRepository;
import com.cosmin.licenta.workday.repository.LanguageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {

    private final EmployeeRepository employeeRepository;

    private final LanguageRepository languageRepository;

    private final LanguageMapper languageMapper;

    private final LanguageReferentialRepository languageReferentialRepository;

    private final LanguageLevelReferentialRepository languageLevelReferentialRepository;

    public LanguageService(EmployeeRepository employeeRepository, LanguageRepository languageRepository, LanguageMapper languageMapper, LanguageReferentialRepository languageReferentialRepository, LanguageLevelReferentialRepository languageLevelReferentialRepository) {
        this.employeeRepository = employeeRepository;
        this.languageRepository = languageRepository;
        this.languageMapper = languageMapper;
        this.languageReferentialRepository = languageReferentialRepository;
        this.languageLevelReferentialRepository = languageLevelReferentialRepository;
    }

    @Transactional
    public List<LanguageDTO> getLanguages(final Long employeeId) {

        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (employeeOptional.isPresent()) {
            Optional<List<Language>> languagesListOptional = this.languageRepository.findByEmployee(employeeOptional.get());
            if (languagesListOptional.isPresent()) {
                return languageMapper.entitiesToDomains(languagesListOptional.get());
            }
        }
        return null;
    }

    public LanguageDTO putLanguage(final LanguageDTO languageDTO, final MultipartFile document) throws IOException {

        languageDTO.setCertification(document.getBytes());
        Optional<LanguageReferential> languageReferentialOptional = languageReferentialRepository.findByLabel(languageDTO.getLanguage().getLabel());
        languageDTO.getLanguage().setId(languageReferentialOptional.get().getId());

        Optional<LanguageLevelReferential> readingLevelReferentialOptional = languageLevelReferentialRepository.findByLabel(languageDTO.getReading().getLabel());
        languageDTO.getReading().setId(readingLevelReferentialOptional.get().getId());

        Optional<LanguageLevelReferential> writingLevelReferentialOptional = languageLevelReferentialRepository.findByLabel(languageDTO.getWriting().getLabel());
        languageDTO.getWriting().setId(writingLevelReferentialOptional.get().getId());

        Optional<LanguageLevelReferential> speakingLevelReferentialOptional = languageLevelReferentialRepository.findByLabel(languageDTO.getSpeaking().getLabel());
        languageDTO.getSpeaking().setId(speakingLevelReferentialOptional.get().getId());

        Optional<LanguageLevelReferential> overallLevelOptional = languageLevelReferentialRepository.findByLabel(languageDTO.getOverallLevel().getLabel());
        languageDTO.getOverallLevel().setId(overallLevelOptional.get().getId());

        languageRepository.save(languageMapper.domainToEntity(languageDTO));
        return languageDTO;
    }
}
