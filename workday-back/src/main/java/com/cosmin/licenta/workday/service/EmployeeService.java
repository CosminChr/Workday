package com.cosmin.licenta.workday.service;

import com.cosmin.licenta.workday.dto.EmployeeDTO;
import com.cosmin.licenta.workday.mapper.EmployeeMapper;
import com.cosmin.licenta.workday.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    public EmployeeDTO getEmployee(final String username) {
        if (employeeRepository.findByUsername(username).isPresent()) {
            return employeeMapper.entityToDomain(employeeRepository.findByUsername(username).get());
        } else throw new RuntimeException();
    }
}
