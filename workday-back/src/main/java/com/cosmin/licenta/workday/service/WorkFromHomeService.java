package com.cosmin.licenta.workday.service;

import com.cosmin.licenta.workday.dto.OvertimeDTO;
import com.cosmin.licenta.workday.dto.WorkFromHomeDTO;
import com.cosmin.licenta.workday.entity.*;
import com.cosmin.licenta.workday.mapper.OvertimeMapper;
import com.cosmin.licenta.workday.mapper.WorkFromHomeMapper;
import com.cosmin.licenta.workday.repository.DayOfWeekReferentialRepository;
import com.cosmin.licenta.workday.repository.EmployeeRepository;
import com.cosmin.licenta.workday.repository.OvertimeRepository;
import com.cosmin.licenta.workday.repository.WorkFromHomeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkFromHomeService {

    private final WorkFromHomeRepository workFromHomeRepository;

    private final WorkFromHomeMapper workFromHomeMapper;

    private final EmployeeRepository employeeRepository;

    private final DayOfWeekReferentialRepository dayOfWeekReferentialRepository;

    public WorkFromHomeService(WorkFromHomeRepository workFromHomeRepository, WorkFromHomeMapper workFromHomeMapper, EmployeeRepository employeeRepository, DayOfWeekReferentialRepository dayOfWeekReferentialRepository) {
        this.workFromHomeRepository = workFromHomeRepository;
        this.workFromHomeMapper = workFromHomeMapper;
        this.employeeRepository = employeeRepository;
        this.dayOfWeekReferentialRepository = dayOfWeekReferentialRepository;
    }

    public WorkFromHomeDTO getWorkFromHome(final Long employeeId) {

        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (employeeOptional.isPresent()) {
            Optional<WorkFromHome> workFromHomeOptional = this.workFromHomeRepository.findByEmployee(employeeOptional.get());
            if (workFromHomeOptional.isPresent()) {
                return workFromHomeMapper.entityToDomain(workFromHomeOptional.get());
            }
        }
        return null;
    }

    public WorkFromHomeDTO putOvertime(final WorkFromHomeDTO workFromHomeDTO) {
        Optional<DayOfWeekReferential> dayOfWeek1ReferentialOptional = dayOfWeekReferentialRepository.findByLabel(workFromHomeDTO.getDayOfWeekDay1().getLabel());
        workFromHomeDTO.getDayOfWeekDay1().setId(dayOfWeek1ReferentialOptional.get().getId());

        Optional<DayOfWeekReferential> dayOfWeek2ReferentialOptional = dayOfWeekReferentialRepository.findByLabel(workFromHomeDTO.getDayOfWeekDay2().getLabel());
        workFromHomeDTO.getDayOfWeekDay1().setId(dayOfWeek2ReferentialOptional.get().getId());

        workFromHomeRepository.save(workFromHomeMapper.domainToEntity(workFromHomeDTO));
        return workFromHomeDTO;
    }
}
