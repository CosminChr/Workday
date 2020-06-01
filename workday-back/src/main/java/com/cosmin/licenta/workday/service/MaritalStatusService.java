package com.cosmin.licenta.workday.service;

import com.cosmin.licenta.workday.dto.MaritalStatusDTO;
import com.cosmin.licenta.workday.entity.*;
import com.cosmin.licenta.workday.mapper.MaritalStatusMapper;
import com.cosmin.licenta.workday.repository.EmployeeRepository;
import com.cosmin.licenta.workday.repository.MaritalStatusReferentialRepository;
import com.cosmin.licenta.workday.repository.MaritalStatusRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Optional;

@Service
public class MaritalStatusService {

    private final EmployeeRepository employeeRepository;

    private final MaritalStatusRepository maritalStatusRepository;

    private final MaritalStatusReferentialRepository maritalStatusReferentialRepository;

    private final MaritalStatusMapper maritalStatusMapper;

    public MaritalStatusService(EmployeeRepository employeeRepository, MaritalStatusRepository maritalStatusRepository, MaritalStatusReferentialRepository maritalStatusReferentialRepository, MaritalStatusMapper maritalStatusMapper) {
        this.employeeRepository = employeeRepository;
        this.maritalStatusRepository = maritalStatusRepository;
        this.maritalStatusReferentialRepository = maritalStatusReferentialRepository;
        this.maritalStatusMapper = maritalStatusMapper;
    }

    @Transactional
    public MaritalStatusDTO getMaritalStatus(final Long employeeId) {

        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (employeeOptional.isPresent()) {
            Optional<MaritalStatus> maritalStatusOptional = maritalStatusRepository.findByEmployee(employeeOptional.get());
            if (maritalStatusOptional.isPresent()) {
                return maritalStatusMapper.entityToDomain(maritalStatusOptional.get());
            }
        }
        return null;
    }

    public MaritalStatusDTO putMaritalStatus(final MaritalStatusDTO maritalStatusDTO, MultipartFile document) throws IOException {

        maritalStatusDTO.setAttestingDocument(document.getBytes());
        Optional<MaritalStatusReferential> maritalStatusReferentialOptional = maritalStatusReferentialRepository.findByLabel(maritalStatusDTO.getMaritalStatus().getLabel());
        maritalStatusDTO.getMaritalStatus().setId(maritalStatusReferentialOptional.get().getId());

        maritalStatusRepository.save(maritalStatusMapper.domainToEntity(maritalStatusDTO));
        return maritalStatusDTO;
    }
}
