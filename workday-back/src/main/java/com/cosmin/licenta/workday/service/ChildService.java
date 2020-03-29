package com.cosmin.licenta.workday.service;

import com.cosmin.licenta.workday.dto.ChildDTO;
import com.cosmin.licenta.workday.dto.HolidayDTO;
import com.cosmin.licenta.workday.entity.Child;
import com.cosmin.licenta.workday.entity.Employee;
import com.cosmin.licenta.workday.mapper.ChildMapper;
import com.cosmin.licenta.workday.repository.ChildRepository;
import com.cosmin.licenta.workday.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChildService {

    private final EmployeeRepository employeeRepository;

    private final ChildRepository childRepository;

    private final ChildMapper childMapper;

    public ChildService(EmployeeRepository employeeRepository, ChildRepository childRepository, ChildMapper childMapper) {
        this.employeeRepository = employeeRepository;
        this.childRepository = childRepository;
        this.childMapper = childMapper;
    }

    public List<ChildDTO> getChildren(final Long employeeId) {

        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (employeeOptional.isPresent()) {
            Optional<List<Child>> childrenListOptional = this.childRepository.findByEmployee(employeeOptional.get());
            if (childrenListOptional.isPresent()) {
                return childMapper.entitiesToDomains(childrenListOptional.get());
            }
        }
        return null;
    }

    public ChildDTO putChild(final ChildDTO childDTO) {
        childRepository.save(childMapper.domainToEntity(childDTO));
        return childDTO;
    }
}
