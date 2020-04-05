package com.cosmin.licenta.workday.service;

import com.cosmin.licenta.workday.dto.PartnerDTO;
import com.cosmin.licenta.workday.entity.Employee;
import com.cosmin.licenta.workday.entity.Partner;
import com.cosmin.licenta.workday.mapper.PartnerMapper;
import com.cosmin.licenta.workday.repository.EmployeeRepository;
import com.cosmin.licenta.workday.repository.PartnerRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class PartnerService {

    private final EmployeeRepository employeeRepository;

    private final PartnerRepository partnerRepository;

    private final PartnerMapper partnerMapper;

    public PartnerService(EmployeeRepository employeeRepository, PartnerRepository partnerRepository, PartnerMapper partnerMapper) {
        this.employeeRepository = employeeRepository;
        this.partnerRepository = partnerRepository;
        this.partnerMapper = partnerMapper;
    }


    public PartnerDTO getPartner(final Long employeeId) {


        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (employeeOptional.isPresent()) {
            Optional<Partner> partnerOptional = partnerRepository.findByEmployee(employeeOptional.get());
            if (partnerOptional.isPresent()) {
                return partnerMapper.entityToDomain(partnerOptional.get());
            }
        }
        return null;
    }

    @Transactional
    public PartnerDTO putPartner(PartnerDTO partnerDTO) {

        if (partnerDTO.getEmployee() != null && partnerDTO.getLastName() == null) {
            Optional<Employee> employeeOptional = employeeRepository.findById(partnerDTO.getEmployee().getId());
            if (employeeOptional.isPresent()) {
                partnerRepository.deleteByEmployee(employeeOptional.get());
            }
        } else {
            partnerRepository.save(partnerMapper.domainToEntity(partnerDTO));
        }
        return partnerDTO;
    }
}
