package com.cosmin.licenta.workday.mapper;

import com.cosmin.licenta.workday.dto.CompanyJobDTO;
import com.cosmin.licenta.workday.entity.CompanyJob;
import com.cosmin.licenta.workday.entity.ContractTypeReferential;
import com.cosmin.licenta.workday.entity.JobFieldReferential;
import com.cosmin.licenta.workday.entity.RequiredExperienceReferential;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RequiredExperienceReferentialMapper.class, ContractTypeReferentialMapper.class, JobFieldReferentialMapper.class, JobApplicationMapper.class})
public interface CompanyJobMapper {

    CompanyJob domainToEntity(final CompanyJobDTO source);

    CompanyJobDTO entityToDomain(final CompanyJob source);

    List<CompanyJob> domainsToEntities(final List<CompanyJobDTO> sourceList);

    List<CompanyJobDTO> entitiesToDomains(final List<CompanyJob> sourceList);
}
