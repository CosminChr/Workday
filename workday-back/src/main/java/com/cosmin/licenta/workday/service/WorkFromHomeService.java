package com.cosmin.licenta.workday.service;

import com.cosmin.licenta.workday.dto.HolidayDTO;
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
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.toList;

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

    @Transactional
    public WorkFromHomeDTO getWorkFromHome(final Long employeeId) {

        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (employeeOptional.isPresent()) {
            Optional<WorkFromHome> workFromHomeOptional = this.workFromHomeRepository.findByEmployee(employeeOptional.get());
            if (workFromHomeOptional.isPresent()) { ;
                return workFromHomeMapper.entityToDomain(workFromHomeOptional.get());
            }
        }
        return null;
    }

    public WorkFromHomeDTO putWorkFromHome(final WorkFromHomeDTO workFromHomeDTO) {

        if (workFromHomeDTO.getDayOfWeekDay1() != null) {
            Optional<DayOfWeekReferential> dayOfWeek1ReferentialOptional = dayOfWeekReferentialRepository.findByLabel(workFromHomeDTO.getDayOfWeekDay1().getLabel());
            workFromHomeDTO.getDayOfWeekDay1().setId(dayOfWeek1ReferentialOptional.get().getId());
        }

        if (workFromHomeDTO.getDayOfWeekDay2() != null) {
            Optional<DayOfWeekReferential> dayOfWeek2ReferentialOptional = dayOfWeekReferentialRepository.findByLabel(workFromHomeDTO.getDayOfWeekDay2().getLabel());
            workFromHomeDTO.getDayOfWeekDay2().setId(dayOfWeek2ReferentialOptional.get().getId());
        }

        if (workFromHomeDTO.getPotentialDayOfWeekDay1() != null) {
            Optional<DayOfWeekReferential> potentialDayOfWeek1ReferentialOptional = dayOfWeekReferentialRepository.findByLabel(workFromHomeDTO.getPotentialDayOfWeekDay1().getLabel());
            workFromHomeDTO.getPotentialDayOfWeekDay1().setId(potentialDayOfWeek1ReferentialOptional.get().getId());
        }

        if (workFromHomeDTO.getPotentialDayOfWeekDay2() != null) {
            Optional<DayOfWeekReferential> potentialDayOfWeek2ReferentialOptional = dayOfWeekReferentialRepository.findByLabel(workFromHomeDTO.getPotentialDayOfWeekDay2().getLabel());
            workFromHomeDTO.getPotentialDayOfWeekDay2().setId(potentialDayOfWeek2ReferentialOptional.get().getId());
        }

        workFromHomeRepository.save(workFromHomeMapper.domainToEntity(workFromHomeDTO));
        return workFromHomeDTO;
    }

    public List<WorkFromHomeDTO> getWorkFromHomeForEmployeesOfManager(Long managerId) {
        final Optional<List<WorkFromHome>> workFromHomeListOptional = workFromHomeRepository.findByManagerId(managerId);

        if (workFromHomeListOptional.isPresent()) {
            return workFromHomeMapper.entitiesToDomains(workFromHomeListOptional.get());
        }

        return null;
    }
}
