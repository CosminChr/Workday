package com.cosmin.licenta.workday.service;

import com.cosmin.licenta.workday.dto.AddressDTO;
import com.cosmin.licenta.workday.dto.HolidayDTO;
import com.cosmin.licenta.workday.entity.Address;
import com.cosmin.licenta.workday.entity.Employee;
import com.cosmin.licenta.workday.entity.Holiday;
import com.cosmin.licenta.workday.mapper.AddressMapper;
import com.cosmin.licenta.workday.mapper.HolidayMapper;
import com.cosmin.licenta.workday.repository.AddressRepository;
import com.cosmin.licenta.workday.repository.EmployeeRepository;
import com.cosmin.licenta.workday.repository.HolidayRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    private AddressRepository addressRepository;

    private AddressMapper addressMapper;

    private EmployeeRepository employeeRepository;

    public AddressService(AddressRepository addressRepository, AddressMapper addressMapper, EmployeeRepository employeeRepository) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
        this.employeeRepository = employeeRepository;
    }

    public List<AddressDTO> getAddresses(final Long employeeId) {

        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (employeeOptional.isPresent()) {
            Optional<List<Address>> addressesListOptional = this.addressRepository.findByEmployee(employeeOptional.get());
            if (addressesListOptional.isPresent()) {
                return addressMapper.entitiesToDomains(addressesListOptional.get());
            }
        }
        return null;
    }

    public AddressDTO putAddress(final AddressDTO address) {
        addressRepository.save(addressMapper.domainToEntity(address));
        return address;
    }
}
