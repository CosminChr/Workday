package com.cosmin.licenta.workday.service;

import com.cosmin.licenta.workday.dto.EmployeeDTO;
import com.cosmin.licenta.workday.entity.DepartmentReferential;
import com.cosmin.licenta.workday.entity.Employee;
import com.cosmin.licenta.workday.entity.GenderReferential;
import com.cosmin.licenta.workday.entity.JobPositionReferential;
import com.cosmin.licenta.workday.mapper.EmployeeMapper;
import com.cosmin.licenta.workday.repository.DepartmentReferentialRepository;
import com.cosmin.licenta.workday.repository.EmployeeRepository;
import com.cosmin.licenta.workday.repository.GenderReferentialRepository;
import com.cosmin.licenta.workday.repository.JobPositionReferentialRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    private EmployeeMapper employeeMapper;

    private GenderReferentialRepository genderReferentialRepository;

    private JobPositionReferentialRepository jobPositionReferentialRepository;

    private DepartmentReferentialRepository departmentReferentialRepository;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper, GenderReferentialRepository genderReferentialRepository, JobPositionReferentialRepository jobPositionReferentialRepository, DepartmentReferentialRepository departmentReferentialRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.genderReferentialRepository = genderReferentialRepository;
        this.jobPositionReferentialRepository = jobPositionReferentialRepository;
        this.departmentReferentialRepository = departmentReferentialRepository;
    }

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

            if (employeeToUpdate.getGender() != null && employeeToUpdate.getGender().getId() == null) {
                Optional<GenderReferential> genderOptional = genderReferentialRepository
                        .findByLabel(employeeToUpdate.getGender().getLabel());
                if (genderOptional.isPresent()) {
                    employeeToUpdate.getGender().setId(genderOptional.get().getId());
                } else {
                    employeeToUpdate.setGender(null);
                }
            }

            if (employeeToUpdate.getJobPosition() != null && employeeToUpdate.getJobPosition().getId() == null) {
                Optional<JobPositionReferential> jobPositionOptional = jobPositionReferentialRepository
                        .findByLabel(employeeToUpdate.getJobPosition().getLabel());
                if (jobPositionOptional.isPresent()) {
                    employeeToUpdate.getJobPosition().setId(jobPositionOptional.get().getId());
                } else {
                    employeeToUpdate.setJobPosition(null);
                }
            }

            if (employeeToUpdate.getDepartment() != null && employeeToUpdate.getDepartment().getId() == null) {
                Optional<DepartmentReferential> departmentOptional = departmentReferentialRepository
                        .findByLabel(employeeToUpdate.getDepartment().getLabel());
                if (departmentOptional.isPresent()) {
                    employeeToUpdate.getDepartment().setId(departmentOptional.get().getId());
                } else {
                    employeeToUpdate.setDepartment(null);
                }
            }

            employeeRepository.save(employeeToUpdate);
            return employeeMapper.entityToDomain(employeeToUpdate);
        }
        return null;
    }
}
