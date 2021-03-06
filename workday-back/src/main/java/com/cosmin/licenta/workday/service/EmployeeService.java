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

import javax.transaction.Transactional;
import java.util.List;
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

    @Transactional
    public EmployeeDTO getEmployee(final String username) {
        if (employeeRepository.findByUsername(username).isPresent()) {
            final Employee employee = employeeRepository.findByUsername(username).get();
            return employeeMapper.entityToDomain(employee);
        }
        return null;
    }

    public EmployeeDTO putEmployee(final EmployeeDTO employeeDTO) {
        if (employeeRepository.findByUsername(employeeDTO.getUsername()).isPresent()) {
            Employee employeeToUpdate = employeeRepository.findByUsername(employeeDTO.getUsername()).get();
            employeeToUpdate = employeeMapper.mergeEntity(employeeToUpdate, employeeDTO);

            if (employeeToUpdate.getGender() != null) {
                Optional<GenderReferential> genderOptional = genderReferentialRepository
                        .findByLabel(employeeToUpdate.getGender().getLabel());
                if (genderOptional.isPresent()) {
                    employeeToUpdate.getGender().setId(genderOptional.get().getId());
                } else {
                    employeeToUpdate.setGender(null);
                }
            }

            if (employeeToUpdate.getJobPosition() != null) {
                Optional<JobPositionReferential> jobPositionOptional = jobPositionReferentialRepository
                        .findByLabel(employeeToUpdate.getJobPosition().getLabel());
                if (jobPositionOptional.isPresent()) {
                    employeeToUpdate.getJobPosition().setId(jobPositionOptional.get().getId());
                } else {
                    employeeToUpdate.setJobPosition(null);
                }
            }

            if (employeeToUpdate.getDepartment() != null) {
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

    public List<EmployeeDTO> getEmployeesBymanagerId(Long managerId) {
        final Optional<List<Employee>> employeesByManagerIdOptional = this.employeeRepository.findByManagerId(managerId);
        if (employeesByManagerIdOptional.isPresent()) {
            return this.employeeMapper.entitiesToDomains(employeesByManagerIdOptional.get());
        }
        return null;
    }

    public EmployeeDTO getManager(Long managerId) {
        final Optional<Employee> managerOptional = employeeRepository.findById(managerId);
        if (managerOptional.isPresent()) {
            return this.employeeMapper.entityToDomain(managerOptional.get());
        }
        return null;
    }

    public List<EmployeeDTO> getEmployees() {
        return employeeMapper.entitiesToDomains(employeeRepository.findAll());
    }
}
