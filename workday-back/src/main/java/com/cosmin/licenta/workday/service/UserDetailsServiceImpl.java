package com.cosmin.licenta.workday.service;

import com.cosmin.licenta.workday.entity.Admin;
import com.cosmin.licenta.workday.entity.Employee;
import com.cosmin.licenta.workday.repository.AdminRepository;
import com.cosmin.licenta.workday.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    AdminRepository adminRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Employee> employeeOptional = employeeRepository.findByUsername(username);
        if (employeeOptional.isPresent()) {
            return UserDetailsImpl.build(employeeOptional.get());
        } else {
            Admin admin = adminRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
            return UserDetailsImpl.build(admin);

        }
    }
}
