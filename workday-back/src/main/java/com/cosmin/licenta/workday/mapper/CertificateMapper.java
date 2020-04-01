package com.cosmin.licenta.workday.mapper;

import com.cosmin.licenta.workday.dto.CertificateDTO;
import com.cosmin.licenta.workday.entity.Certificate;
import com.cosmin.licenta.workday.entity.CertificateTypeReferential;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CertificateTypeReferentialMapper.class, EmployeeMapper.class})
public interface CertificateMapper {

    Certificate domainToEntity(final CertificateDTO source);

    CertificateDTO entityToDomain(final Certificate source);

    List<Certificate> domainsToEntities(final List<CertificateDTO> sourceList);

    List<CertificateDTO> entitiesToDomains(final List<Certificate> sourceList);
}
