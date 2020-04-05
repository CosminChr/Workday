package com.cosmin.licenta.workday.service;

import com.cosmin.licenta.workday.dto.CitizenshipDTO;
import com.cosmin.licenta.workday.entity.Citizenship;
import com.cosmin.licenta.workday.entity.Employee;
import com.cosmin.licenta.workday.mapper.CitizenshipMapper;
import com.cosmin.licenta.workday.repository.CitizenshipRepository;
import com.cosmin.licenta.workday.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitizenshipService {

    private final EmployeeRepository employeeRepository;

    private final CitizenshipRepository citizenshipRepository;

    private final CitizenshipMapper citizenshipMapper;

    public CitizenshipService(EmployeeRepository employeeRepository, CitizenshipRepository citizenshipRepository, CitizenshipMapper citizenshipMapper) {
        this.employeeRepository = employeeRepository;
        this.citizenshipRepository = citizenshipRepository;
        this.citizenshipMapper = citizenshipMapper;
    }


    public List<CitizenshipDTO> getCitizenships(final Long employeeId) {

        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (employeeOptional.isPresent()) {
            Optional<List<Citizenship>> citizenshipListOptional = this.citizenshipRepository.findByEmployee(employeeOptional.get());
            if (citizenshipListOptional.isPresent()) {
                return citizenshipMapper.entitiesToDomains(citizenshipListOptional.get());
            }
        }
        return null;
    }

    public CitizenshipDTO putCitizenship(final CitizenshipDTO citizenshipDTO) {
        citizenshipRepository.save(citizenshipMapper.domainToEntity(citizenshipDTO));
        return citizenshipDTO;
    }
}
