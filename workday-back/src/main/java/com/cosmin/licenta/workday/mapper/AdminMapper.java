package com.cosmin.licenta.workday.mapper;

import com.cosmin.licenta.workday.dto.AdminDTO;
import com.cosmin.licenta.workday.dto.EmployeeDTO;
import com.cosmin.licenta.workday.entity.Admin;
import com.cosmin.licenta.workday.entity.Employee;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RoleReferentialMapper.class})
public interface AdminMapper {

    Admin domainToEntity(final AdminDTO source);

    AdminDTO entityToDomain(final Admin source);

    List<Admin> domainsToEntities(final List<AdminDTO> sourceList);

    List<AdminDTO> entitiesToDomains(final List<Admin> sourceList);
}
