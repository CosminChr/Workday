package com.cosmin.licenta.workday.service;

import com.cosmin.licenta.workday.dto.AcademicStudyDTO;
import com.cosmin.licenta.workday.entity.*;
import com.cosmin.licenta.workday.mapper.AcademicStudyMapper;
import com.cosmin.licenta.workday.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AcademicStudyService {

    private final EmployeeRepository employeeRepository;

    private final AcademicStudyRepository academicStudyRepository;

    private final AcademicStudyMapper academicStudyMapper;

    private final StudyLevelReferentialRepository studyLevelReferentialRepository;

    private final StudyFieldReferentialRepository studyFieldReferentialRepository;

    private final CountryReferentialRepository countryReferentialRepository;


    public AcademicStudyService(EmployeeRepository employeeRepository, AcademicStudyRepository academicStudyRepository, AcademicStudyMapper academicStudyMapper, StudyLevelReferentialRepository studyLevelReferentialRepository, StudyFieldReferentialRepository studyFieldReferentialRepository, CountryReferentialRepository countryReferentialRepository) {
        this.employeeRepository = employeeRepository;
        this.academicStudyRepository = academicStudyRepository;
        this.academicStudyMapper = academicStudyMapper;
        this.studyLevelReferentialRepository = studyLevelReferentialRepository;
        this.studyFieldReferentialRepository = studyFieldReferentialRepository;
        this.countryReferentialRepository = countryReferentialRepository;
    }

    public List<AcademicStudyDTO> getAcademicStudies(final Long employeeId) {

        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (employeeOptional.isPresent()) {
            Optional<List<AcademicStudy>> academicStudiesOptionalList;
            academicStudiesOptionalList = this.academicStudyRepository.findByEmployee(employeeOptional.get());
            if (academicStudiesOptionalList.isPresent()) {
                return academicStudyMapper.entitiesToDomains(academicStudiesOptionalList.get());
            }
        }
        return null;
    }

    public AcademicStudyDTO putAcademicStudy(final AcademicStudyDTO academicStudyDTO) {
        Optional<StudyLevelReferential> studyLevelReferentialOptional = studyLevelReferentialRepository.findByLabel(academicStudyDTO.getStudyLevel().getLabel());
        academicStudyDTO.getStudyLevel().setId(studyLevelReferentialOptional.get().getId());

        Optional<StudyFieldReferential> studyFieldReferentialOptional = studyFieldReferentialRepository.findByLabel(academicStudyDTO.getStudyField().getLabel());
        academicStudyDTO.getStudyField().setId(studyFieldReferentialOptional.get().getId());

        Optional<CountryReferential> countryReferentialOptional = countryReferentialRepository.findByLabel(academicStudyDTO.getCountry().getLabel());
        academicStudyDTO.getCountry().setId(countryReferentialOptional.get().getId());


       academicStudyRepository.save(academicStudyMapper.domainToEntity(academicStudyDTO));
       return academicStudyDTO;
    }
}
