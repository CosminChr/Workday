package com.cosmin.licenta.workday.service;

import com.cosmin.licenta.workday.dto.HolidayDTO;
import com.cosmin.licenta.workday.entity.Employee;
import com.cosmin.licenta.workday.entity.Holiday;
import com.cosmin.licenta.workday.entity.HolidayReferential;
import com.cosmin.licenta.workday.mapper.HolidayMapper;
import com.cosmin.licenta.workday.repository.EmployeeRepository;
import com.cosmin.licenta.workday.repository.HolidayReferentialRepository;
import com.cosmin.licenta.workday.repository.HolidayRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HolidayService {

    private final  HolidayRepository holidayRepository;

    private final  HolidayMapper holidayMapper;

    private final EmployeeRepository employeeRepository;

    private final HolidayReferentialRepository holidayReferentialRepository;

    public HolidayService(final HolidayRepository holidayRepository, HolidayMapper holidayMapper, EmployeeRepository employeeRepository, HolidayReferentialRepository holidayReferentialRepository) {
        this.holidayRepository = holidayRepository;
        this.holidayMapper = holidayMapper;
        this.employeeRepository = employeeRepository;
        this.holidayReferentialRepository = holidayReferentialRepository;
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

    public HolidayDTO putHoliday(final HolidayDTO holiday) {
        Optional<HolidayReferential> holidayReferential = holidayReferentialRepository.findByLabel(holiday.getHolidayType().getLabel());
        holiday.getHolidayType().setId(holidayReferential.get().getId());

        holidayRepository.save(holidayMapper.domainToEntity(holiday));
        return holiday;
    }

}
