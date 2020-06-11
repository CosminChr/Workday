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
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
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

    @Transactional
    public List<HolidayDTO> getHolidays(final Long employeeId) {

        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (employeeOptional.isPresent()) {
            Optional<List<Holiday>> holidaysListOptional = this.holidayRepository.findByEmployeeId(employeeOptional.get().getId());
            if (holidaysListOptional.isPresent()) {
                return holidayMapper.entitiesToDomains(holidaysListOptional.get());
            }
        }
        return null;
    }

    public HolidayDTO putHolidayRequest(final HolidayDTO holiday)  {
        Optional<HolidayReferential> holidayReferential = holidayReferentialRepository.findByLabel(holiday.getHolidayType().getLabel());
        holiday.getHolidayType().setId(holidayReferential.get().getId());
        holidayRepository.save(holidayMapper.domainToEntity(holiday));
        return holiday;
    }

    @Transactional
    public List<HolidayDTO> getHolidays(final List<Long> employeeIds) {
        final Optional<List<Holiday>> holidaysByEmployeeId = holidayRepository.findByEmployeeId(employeeIds);
        if (holidaysByEmployeeId.isPresent() && !CollectionUtils.isEmpty(holidaysByEmployeeId.get())) {
            return holidayMapper.entitiesToDomains(holidaysByEmployeeId.get());
        }
        return null;
    }

    @Transactional
    public List<HolidayDTO> getHolidaysHandledByManager(final Long managerId) {

        final Optional<Employee> manager = employeeRepository.findById(managerId);

        if (manager.isPresent()) {
            final Optional<List<Holiday>> holidaysByManagerId = holidayRepository.findByManagerId(manager.get().getId());

            if (holidaysByManagerId.isPresent() && !CollectionUtils.isEmpty(holidaysByManagerId.get())) {
                return holidayMapper.entitiesToDomains(holidaysByManagerId.get());
            }
        }

        return null;
    }


    public HolidayDTO getHoliday(final Long holidayId) {

        final Optional<Holiday> holidayOptional = holidayRepository.findById(holidayId);

        if (holidayOptional.isPresent()) {
            return holidayMapper.entityToDomain(holidayOptional.get());
        }
        return null;
    }
}
