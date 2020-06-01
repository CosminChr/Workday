package com.cosmin.licenta.workday.service;

import com.cosmin.licenta.workday.dto.ChildDTO;
import com.cosmin.licenta.workday.entity.Child;
import com.cosmin.licenta.workday.entity.Employee;
import com.cosmin.licenta.workday.entity.GenderReferential;
import com.cosmin.licenta.workday.mapper.ChildMapper;
import com.cosmin.licenta.workday.repository.ChildRepository;
import com.cosmin.licenta.workday.repository.EmployeeRepository;
import com.cosmin.licenta.workday.repository.GenderReferentialRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ChildService {

    private final EmployeeRepository employeeRepository;

    private final ChildRepository childRepository;

    private final ChildMapper childMapper;

    private final GenderReferentialRepository genderReferentialRepository;

    public ChildService(EmployeeRepository employeeRepository, ChildRepository childRepository, ChildMapper childMapper, GenderReferentialRepository genderReferentialRepository) {
        this.employeeRepository = employeeRepository;
        this.childRepository = childRepository;
        this.childMapper = childMapper;
        this.genderReferentialRepository = genderReferentialRepository;
    }

    @Transactional
    public List<ChildDTO> getChildren(final Long employeeId) {

        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (employeeOptional.isPresent()) {
            Optional<List<Child>> childrenListOptional = this.childRepository.findByEmployee(employeeOptional.get());
            if (childrenListOptional.isPresent()) {
                return childMapper.entitiesToDomains(childrenListOptional.get());
            }
        }
        return null;
    }

    public ChildDTO putChild(final ChildDTO childDTO, final MultipartFile document) throws IOException {

        childDTO.setAttestingDocument(document.getBytes());
        Optional<GenderReferential> genderOptional = genderReferentialRepository.findByLabel(childDTO.getGender().getLabel());
        childDTO.getGender().setId(genderOptional.get().getId());

        childRepository.save(childMapper.domainToEntity(childDTO));
        return childDTO;
    }
}
