package com.cosmin.licenta.workday.mapper;

import com.cosmin.licenta.workday.dto.EmployeeDTO;
import com.cosmin.licenta.workday.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RoleReferentialMapper.class,
        GenderReferentialMapper.class,
        JobPositionReferentialMapper.class,
        DepartmentReferentialMapper.class})
public interface EmployeeMapper {

    Employee domainToEntity(final EmployeeDTO source);

    EmployeeDTO entityToDomain(final Employee source);

    List<Employee> domainsToEntities(final List<EmployeeDTO> sourceList);

    List<EmployeeDTO> entitiesToDomains(final List<Employee> sourceList);

    @Mappings({
            @org.mapstruct.Mapping(target = "username", ignore = true),
            @org.mapstruct.Mapping(target = "password", ignore = true)
    })
    Employee mergeEntity(@MappingTarget Employee employeeFromDatabase, EmployeeDTO employeeFromClient);
}
