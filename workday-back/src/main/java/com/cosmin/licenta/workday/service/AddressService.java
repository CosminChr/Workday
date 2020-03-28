package com.cosmin.licenta.workday.service;

import com.cosmin.licenta.workday.dto.AddressDTO;
import com.cosmin.licenta.workday.entity.*;
import com.cosmin.licenta.workday.mapper.AddressMapper;
import com.cosmin.licenta.workday.mapper.LocalityReferentialMapper;
import com.cosmin.licenta.workday.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    private final AddressMapper addressMapper;

    private final EmployeeRepository employeeRepository;

    private final AddressTypeReferentialRepository addressTypeReferentialRepository;

    private final CountyReferentialRepository countyReferentialRepository;

    private final CountryReferentialRepository countryReferentialRepository;

    private final LocalityReferentialRepository localityReferentialRepository;

    private final LocalityReferentialMapper localityReferentialMapper;

    public AddressService(AddressRepository addressRepository, AddressMapper addressMapper, EmployeeRepository employeeRepository, AddressTypeReferentialRepository addressTypeReferentialRepository, CountyReferentialRepository countyReferentialRepository, CountryReferentialRepository countryReferentialRepository, LocalityReferentialRepository localityReferentialRepository, LocalityReferentialMapper localityReferentialMapper) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
        this.employeeRepository = employeeRepository;
        this.addressTypeReferentialRepository = addressTypeReferentialRepository;
        this.countyReferentialRepository = countyReferentialRepository;
        this.countryReferentialRepository = countryReferentialRepository;
        this.localityReferentialRepository = localityReferentialRepository;
        this.localityReferentialMapper = localityReferentialMapper;
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
        Optional<AddressTypeReferential> addressTypeOptional = addressTypeReferentialRepository.findByLabel(address.getAddressType().getLabel());
        address.getAddressType().setId(addressTypeOptional.get().getId());
        Optional<CountyReferential> countyOptional = countyReferentialRepository.findByLabel(address.getLocality().getCounty().getLabel());
        Optional<CountryReferential> countryOptional = countryReferentialRepository.findByLabel(address.getLocality().getCountry().getLabel());
        Optional<LocalityReferential> localityReferentialOptional = localityReferentialRepository.findByCountyAndCountry(countyOptional.get(), countryOptional.get());
        address.setLocality(localityReferentialMapper.entityToDomain(localityReferentialOptional.get()));
        addressRepository.save(addressMapper.domainToEntity(address));
        return address;
    }
}
