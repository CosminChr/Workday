package com.cosmin.licenta.workday.service;

import com.cosmin.licenta.workday.dto.AdminDTO;
import com.cosmin.licenta.workday.entity.Admin;
import com.cosmin.licenta.workday.mapper.AdminMapper;
import com.cosmin.licenta.workday.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;

@Service
public class AdminService {

    @Autowired
    private final AdminRepository adminRepository;

    private final AdminMapper adminMapper;

    public AdminService(AdminRepository adminRepository, AdminMapper adminMapper) {
        this.adminRepository = adminRepository;
        this.adminMapper = adminMapper;
    }

    @Transactional
    public AdminDTO getAdmin(final String username) {
        if (adminRepository.findByUsername(username).isPresent()) {
            final Admin admin = adminRepository.findByUsername(username).get();
            return adminMapper.entityToDomain(admin);
        }
        return null;
    }

}
