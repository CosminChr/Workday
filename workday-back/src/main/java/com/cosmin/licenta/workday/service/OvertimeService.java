package com.cosmin.licenta.workday.service;

import com.cosmin.licenta.workday.dto.OvertimeDTO;
import com.cosmin.licenta.workday.entity.Employee;
import com.cosmin.licenta.workday.entity.Overtime;
import com.cosmin.licenta.workday.mapper.OvertimeMapper;
import com.cosmin.licenta.workday.repository.EmployeeRepository;
import com.cosmin.licenta.workday.repository.OvertimeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class OvertimeService {

    private final OvertimeRepository overtimeRepository;

    private final OvertimeMapper overtimeMapper;

    private final EmployeeRepository employeeRepository;

    public OvertimeService(OvertimeRepository overtimeRepository, OvertimeMapper overtimeMapper, EmployeeRepository employeeRepository) {
        this.overtimeRepository = overtimeRepository;
        this.overtimeMapper = overtimeMapper;
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public List<OvertimeDTO> getOvertimeHistory(final Long employeeId) {

        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (employeeOptional.isPresent()) {
            Optional<List<Overtime>> overtimeHistoryOptional = this.overtimeRepository.findByEmployee(employeeOptional.get());
            if (overtimeHistoryOptional.isPresent()) {
                return overtimeMapper.entitiesToDomains(overtimeHistoryOptional.get());
            }
        }
        return null;
    }

    public OvertimeDTO putOvertime(final OvertimeDTO overtimeDTO) {

        overtimeRepository.save(overtimeMapper.domainToEntity(overtimeDTO));
        return overtimeDTO;
    }
}
