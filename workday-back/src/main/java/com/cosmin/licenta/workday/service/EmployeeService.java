package com.cosmin.licenta.workday.service;

import com.cosmin.licenta.workday.dto.EmployeeDTO;
import com.cosmin.licenta.workday.entity.Employee;
import com.cosmin.licenta.workday.entity.GenderReferential;
import com.cosmin.licenta.workday.mapper.EmployeeMapper;
import com.cosmin.licenta.workday.repository.EmployeeRepository;
import com.cosmin.licenta.workday.repository.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private GenderRepository genderRepository;

    public EmployeeDTO getEmployee(final String username) {
        if (employeeRepository.findByUsername(username).isPresent()) {
            return employeeMapper.entityToDomain(employeeRepository.findByUsername(username).get());
        }
        return null;
    }

    public EmployeeDTO putEmployee(final EmployeeDTO employeeDTO) {
        if (employeeRepository.findByUsername(employeeDTO.getUsername()).isPresent()) {
            Employee employeeToUpdate = employeeRepository.findByUsername(employeeDTO.getUsername()).get();
            employeeToUpdate = employeeMapper.mergeEntity(employeeToUpdate, employeeDTO);

            Optional<GenderReferential> genderOptional = genderRepository.findByLabel(employeeToUpdate.getGender().getLabel());
            if (genderOptional.isPresent()) {
                employeeToUpdate.getGender().setId(genderOptional.get().getId());
            }

            employeeRepository.save(employeeToUpdate);
            return employeeMapper.entityToDomain(employeeToUpdate);
        }
        return null;
    }
}
