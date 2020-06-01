package com.cosmin.licenta.workday.service;

import com.cosmin.licenta.workday.dto.CitizenshipDTO;
import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.entity.Citizenship;
import com.cosmin.licenta.workday.entity.Employee;
import com.cosmin.licenta.workday.entity.NationalityReferential;
import com.cosmin.licenta.workday.mapper.CitizenshipMapper;
import com.cosmin.licenta.workday.mapper.NationalityReferentialMapper;
import com.cosmin.licenta.workday.repository.CitizenshipRepository;
import com.cosmin.licenta.workday.repository.EmployeeRepository;
import com.cosmin.licenta.workday.repository.NationalityReferentialRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NationalityService {

    private final EmployeeRepository employeeRepository;

    private final NationalityReferentialRepository nationalityReferentialRepository;

    private final NationalityReferentialMapper nationalityReferentialMapper;

    public NationalityService(EmployeeRepository employeeRepository, NationalityReferentialRepository nationalityReferentialRepository, NationalityReferentialMapper nationalityReferentialMapper) {
        this.employeeRepository = employeeRepository;
        this.nationalityReferentialRepository = nationalityReferentialRepository;
        this.nationalityReferentialMapper = nationalityReferentialMapper;
    }

    public ReferentialDTO getNationality(final Long employeeId) {

        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (employeeOptional.isPresent()) {
            NationalityReferential nationalityReferential = employeeOptional.get().getNationality();
                return nationalityReferentialMapper.entityToDomain(nationalityReferential);
        }
        return null;
    }

    public ReferentialDTO putNationality(final ReferentialDTO nationality, Long employeeId) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (employeeOptional.isPresent()) {
            employeeOptional.get().setNationality(nationalityReferentialMapper.domainToEntity(nationality));
            employeeRepository.save(employeeOptional.get());
        }
        return nationality;
    }
}
