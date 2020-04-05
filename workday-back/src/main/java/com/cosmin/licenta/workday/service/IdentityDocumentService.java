package com.cosmin.licenta.workday.service;

import com.cosmin.licenta.workday.dto.IdentityDocumentDTO;
import com.cosmin.licenta.workday.entity.CountryReferential;
import com.cosmin.licenta.workday.entity.Employee;
import com.cosmin.licenta.workday.entity.IdentityDocument;
import com.cosmin.licenta.workday.entity.IdentityDocumentTypeReferential;
import com.cosmin.licenta.workday.mapper.IdentityDocumentMapper;
import com.cosmin.licenta.workday.repository.CountryReferentialRepository;
import com.cosmin.licenta.workday.repository.EmployeeRepository;
import com.cosmin.licenta.workday.repository.IdentityDocumentRepository;
import com.cosmin.licenta.workday.repository.IdentityDocumentTypeReferentialRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IdentityDocumentService {

    private final IdentityDocumentRepository identityDocumentRepository;

    private final IdentityDocumentMapper identityDocumentMapper;

    private final EmployeeRepository employeeRepository;

    private final IdentityDocumentTypeReferentialRepository identityDocumentTypeReferentialRepository;

    private final CountryReferentialRepository countryReferentialRepository;

    public IdentityDocumentService(IdentityDocumentRepository identityDocumentRepository, IdentityDocumentMapper identityDocumentMapper, EmployeeRepository employeeRepository, IdentityDocumentTypeReferentialRepository identityDocumentTypeReferentialRepository, CountryReferentialRepository countryReferentialRepository) {
        this.identityDocumentRepository = identityDocumentRepository;
        this.identityDocumentMapper = identityDocumentMapper;
        this.employeeRepository = employeeRepository;
        this.identityDocumentTypeReferentialRepository = identityDocumentTypeReferentialRepository;
        this.countryReferentialRepository = countryReferentialRepository;
    }

    public List<IdentityDocumentDTO> getIdentityDocuments(final Long employeeId) {

        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (employeeOptional.isPresent()) {
            Optional<List<IdentityDocument>> identityDocumentsListOptional = this.identityDocumentRepository.findByEmployee(employeeOptional.get());
            if (identityDocumentsListOptional.isPresent()) {
                return identityDocumentMapper.entitiesToDomains(identityDocumentsListOptional.get());
            }
        }
        return null;
    }

    public IdentityDocumentDTO putIdentityDocument(final IdentityDocumentDTO identityDocument) {
        Optional<IdentityDocumentTypeReferential> identityDocumentTypeReferentialOptional = identityDocumentTypeReferentialRepository.findByLabel(identityDocument.getIdentityDocumentType().getLabel());
        Optional<CountryReferential> countryReferentialOptional = countryReferentialRepository.findByLabel(identityDocument.getCountry().getLabel());

        identityDocument.getIdentityDocumentType().setId(identityDocumentTypeReferentialOptional.get().getId());
        identityDocument.getCountry().setId(countryReferentialOptional.get().getId());

        identityDocumentRepository.save(identityDocumentMapper.domainToEntity(identityDocument));
        return identityDocument;
    }

}
