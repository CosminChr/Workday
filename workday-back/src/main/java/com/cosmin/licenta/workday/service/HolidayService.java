package com.cosmin.licenta.workday.service;

import com.cosmin.licenta.workday.dto.HolidayDTO;
import com.cosmin.licenta.workday.entity.Employee;
import com.cosmin.licenta.workday.entity.Holiday;
import com.cosmin.licenta.workday.mapper.HolidayMapper;
import com.cosmin.licenta.workday.repository.EmployeeRepository;
import com.cosmin.licenta.workday.repository.HolidayRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HolidayService {

    private HolidayRepository holidayRepository;

    private HolidayMapper holidayMapper;

    private EmployeeRepository employeeRepository;

    public HolidayService(final HolidayRepository holidayRepository, HolidayMapper holidayMapper, EmployeeRepository employeeRepository) {
        this.holidayRepository = holidayRepository;
        this.holidayMapper = holidayMapper;
        this.employeeRepository = employeeRepository;
    }

    public List<HolidayDTO> getHolidays(final Long employeeId) {

        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (employeeOptional.isPresent()) {
            Optional<List<Holiday>> holidaysListOptional = this.holidayRepository.findByEmployee(employeeOptional.get());
            if (holidaysListOptional.isPresent()) {
                return holidayMapper.entitiesToDomains(holidaysListOptional.get());
            }
        }
        return null;
    }
}
