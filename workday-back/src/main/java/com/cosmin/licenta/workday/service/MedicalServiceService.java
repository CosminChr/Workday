package com.cosmin.licenta.workday.service;

import com.cosmin.licenta.workday.dto.MedicalServiceDTO;
import com.cosmin.licenta.workday.entity.Employee;
import com.cosmin.licenta.workday.entity.MedicalService;
import com.cosmin.licenta.workday.entity.MedicalServiceProviderReferential;
import com.cosmin.licenta.workday.mapper.EmployeeMapper;
import com.cosmin.licenta.workday.mapper.MedicalServiceMapper;
import com.cosmin.licenta.workday.repository.EmployeeRepository;
import com.cosmin.licenta.workday.repository.MedicalServiceProviderReferentialRepository;
import com.cosmin.licenta.workday.repository.MedicalServiceRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MedicalServiceService {

    private final MedicalServiceRepository medicalServiceRepository;

    private final MedicalServiceMapper medicalServiceMapper;

    private final EmployeeRepository employeeRepository;

    private final MedicalServiceProviderReferentialRepository medicalServiceProviderReferentialRepository;

    private final EmployeeMapper employeeMapper;

    public MedicalServiceService(MedicalServiceRepository medicalServiceRepository, MedicalServiceMapper medicalServiceMapper, EmployeeRepository employeeRepository, MedicalServiceProviderReferentialRepository medicalServiceProviderReferentialRepository, EmployeeMapper employeeMapper) {
        this.medicalServiceRepository = medicalServiceRepository;
        this.medicalServiceMapper = medicalServiceMapper;
        this.employeeRepository = employeeRepository;
        this.medicalServiceProviderReferentialRepository = medicalServiceProviderReferentialRepository;
        this.employeeMapper = employeeMapper;
    }

    public MedicalServiceDTO getMedicalService(final Long employeeId) {

        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (employeeOptional.isPresent()) {
            Optional<MedicalService> medicalServiceOptional = this.medicalServiceRepository.findByEmployee(employeeOptional.get());
            if (medicalServiceOptional.isPresent()) {
                return medicalServiceMapper.entityToDomain(medicalServiceOptional.get());
            }
        }
        return null;
    }

    public MedicalServiceDTO putMedicalService(final MedicalServiceDTO medicalServiceDTO) {
        this.medicalServiceRepository.deleteByEmployee(employeeMapper.domainToEntity(medicalServiceDTO.getEmployee()));

        Optional<MedicalServiceProviderReferential> medicalServiceProviderOptional = medicalServiceProviderReferentialRepository.findByLabel(medicalServiceDTO.getMedicalServiceProvider().getLabel());
        medicalServiceDTO.getMedicalServiceProvider().setId(medicalServiceProviderOptional.get().getId());

        medicalServiceRepository.save(medicalServiceMapper.domainToEntity(medicalServiceDTO));
        return medicalServiceDTO;
    }
}
