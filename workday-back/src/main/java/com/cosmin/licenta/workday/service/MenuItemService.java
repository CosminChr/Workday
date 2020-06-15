package com.cosmin.licenta.workday.service;

import com.cosmin.licenta.workday.dto.MenuItemDTO;
import com.cosmin.licenta.workday.entity.Admin;
import com.cosmin.licenta.workday.entity.Employee;
import com.cosmin.licenta.workday.entity.MenuItem;
import com.cosmin.licenta.workday.mapper.MenuItemMapper;
import com.cosmin.licenta.workday.repository.AdminRepository;
import com.cosmin.licenta.workday.repository.EmployeeRepository;
import com.cosmin.licenta.workday.repository.MenuItemRepository;
import com.cosmin.licenta.workday.util.RoleTypeReferentialEnum;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;

    private final MenuItemMapper menuItemMapper;

    private final EmployeeRepository employeeRepository;

    private final AdminRepository adminRepository;

    public MenuItemService(MenuItemRepository menuItemRepository, MenuItemMapper menuItemMapper, EmployeeRepository employeeRepository, AdminRepository adminRepository) {
        this.menuItemRepository = menuItemRepository;
        this.menuItemMapper = menuItemMapper;
        this.employeeRepository = employeeRepository;
        this.adminRepository = adminRepository;
    }

    public List<MenuItemDTO> getMenuItemsForEmployee(final Long userId) {
        Optional<Employee> employeeOptional = employeeRepository.findById(userId);
        if (employeeOptional.isPresent()) {
            final Employee employee = employeeOptional.get();
            final List<MenuItem> menuItems = menuItemRepository.findAll();
            if (employee.getRoles().stream().anyMatch(role -> role.getLabel().equals(RoleTypeReferentialEnum.MANAGER.getLabel()))) {
                return menuItemMapper.entitiesToDomains(menuItems.subList(0, menuItems.size() - 2));
            } else if (employee.getRoles().stream().anyMatch(role -> role.getLabel().equals(RoleTypeReferentialEnum.EMPLOYEE.getLabel()))) {
                return menuItemMapper.entitiesToDomains(menuItems.subList(0, menuItems.size() - 3));
            }
        }
        return null;
    }

    public List<MenuItemDTO> getMenuItemsForAdmin(final Long userId) {
        Optional<Admin> adminOptional = adminRepository.findById(userId);
        if (adminOptional.isPresent()) {
            final List<MenuItem> menuItems = menuItemRepository.findAll();
            return menuItemMapper.entitiesToDomains(menuItems.subList(menuItems.size() - 2, menuItems.size()));
        }
        return null;
    }
}
