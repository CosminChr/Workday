package com.cosmin.licenta.workday.service;

import com.cosmin.licenta.workday.dto.ReferentialDTO;
import com.cosmin.licenta.workday.entity.CertificateTypeReferential;
import com.cosmin.licenta.workday.entity.ContractTypeReferential;
import com.cosmin.licenta.workday.entity.DayOfWeekReferential;
import com.cosmin.licenta.workday.entity.DepartmentReferential;
import com.cosmin.licenta.workday.mapper.CertificateTypeReferentialMapper;
import com.cosmin.licenta.workday.mapper.ContractTypeReferentialMapper;
import com.cosmin.licenta.workday.mapper.DayOfWeekReferentialMapper;
import com.cosmin.licenta.workday.mapper.DepartmentReferentialMapper;
import com.cosmin.licenta.workday.repository.CertificateTypeReferentialRepository;
import com.cosmin.licenta.workday.repository.ContractTypeReferentialRepository;
import com.cosmin.licenta.workday.repository.DayOfWeekReferentialRepository;
import com.cosmin.licenta.workday.repository.DepartmentReferentialRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;

@Service
public class DepartmentReferentialService {

    private final DepartmentReferentialRepository departmentReferentialRepository;

    private final DepartmentReferentialMapper departmentReferentialMapper;

    public DepartmentReferentialService(DepartmentReferentialRepository departmentReferentialRepository, DepartmentReferentialMapper departmentReferentialMapper) {
        this.departmentReferentialRepository = departmentReferentialRepository;
        this.departmentReferentialMapper = departmentReferentialMapper;
    }

    public List<ReferentialDTO> getDepartmentReferentials() {
        List<DepartmentReferential> departmentReferentials = departmentReferentialRepository.findAll();
        return departmentReferentialMapper.entitiesToDomains(departmentReferentials);
    }
}
