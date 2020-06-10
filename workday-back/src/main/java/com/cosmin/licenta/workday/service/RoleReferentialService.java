package com.cosmin.licenta.workday.service;

import com.cosmin.licenta.workday.entity.EmployeeRole;
import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.entity.RoleReferential;
import com.cosmin.licenta.workday.mapper.RoleReferentialMapper;
import com.cosmin.licenta.workday.repository.EmployeeRoleRepository;
import com.cosmin.licenta.workday.repository.RoleReferentialRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleReferentialService {

    private final EmployeeRoleRepository employeeRoleRepository;

    private final RoleReferentialMapper roleReferentialMapper;

    private final RoleReferentialRepository roleReferentialRepository;

    public RoleReferentialService(EmployeeRoleRepository employeeRoleRepository, RoleReferentialMapper roleReferentialMapper, RoleReferentialRepository roleReferentialRepository) {
        this.employeeRoleRepository = employeeRoleRepository;
        this.roleReferentialMapper = roleReferentialMapper;
        this.roleReferentialRepository = roleReferentialRepository;
    }

    public List<ReferentialDTO> getRoleReferentials(Long employeeId) {
        Optional<List<EmployeeRole>> employeeRoleReferentialsOptional = employeeRoleRepository.findRolesForEmployee(employeeId);
        if (employeeRoleReferentialsOptional.isPresent()) {
            final List<ReferentialDTO> roleReferentials= new ArrayList<>();
            long counter = 0;
            for (EmployeeRole e: employeeRoleReferentialsOptional.get()) {
                ReferentialDTO referentialDTO = new ReferentialDTO();
                referentialDTO.setId(++counter);
                Optional<RoleReferential> roleOptional = roleReferentialRepository.findById(e.getId().getRoleId());
                if (roleOptional.isPresent()) {
                    referentialDTO.setLabel(roleOptional.get().getLabel());
                }
                roleReferentials.add(referentialDTO);
            }
            return roleReferentials;
        }
        return null;
    }
}
