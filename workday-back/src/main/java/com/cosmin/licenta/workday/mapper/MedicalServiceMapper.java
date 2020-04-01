package com.cosmin.licenta.workday.mapper;

import com.cosmin.licenta.workday.dto.MedicalServiceDTO;
import com.cosmin.licenta.workday.entity.MedicalService;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {MedicalServiceProviderReferentialMapper.class, EmployeeMapper.class})
public interface MedicalServiceMapper {

    MedicalService domainToEntity(final MedicalServiceDTO source);

    MedicalServiceDTO entityToDomain(final MedicalService source);

    List<MedicalService> domainsToEntities(final List<MedicalServiceDTO> sourceList);

    List<MedicalServiceDTO> entitiesToDomains(final List<MedicalService> sourceList);
}
