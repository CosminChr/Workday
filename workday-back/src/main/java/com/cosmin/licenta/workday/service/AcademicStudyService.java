package com.cosmin.licenta.workday.service;

import com.cosmin.licenta.workday.dto.AcademicStudyDTO;
import com.cosmin.licenta.workday.dto.BankAccountDTO;
import com.cosmin.licenta.workday.entity.*;
import com.cosmin.licenta.workday.mapper.AcademicStudyMapper;
import com.cosmin.licenta.workday.mapper.BankAccountMapper;
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

    public AcademicStudyService(EmployeeRepository employeeRepository, AcademicStudyRepository academicStudyRepository, AcademicStudyMapper academicStudyMapper, StudyLevelReferentialRepository studyLevelReferentialRepository, StudyFieldReferentialRepository studyFieldReferentialRepository) {
        this.employeeRepository = employeeRepository;
        this.academicStudyRepository = academicStudyRepository;
        this.academicStudyMapper = academicStudyMapper;
        this.studyLevelReferentialRepository = studyLevelReferentialRepository;
        this.studyFieldReferentialRepository = studyFieldReferentialRepository;
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


       academicStudyRepository.save(academicStudyMapper.domainToEntity(academicStudyDTO));
       return academicStudyDTO;
    }
}
