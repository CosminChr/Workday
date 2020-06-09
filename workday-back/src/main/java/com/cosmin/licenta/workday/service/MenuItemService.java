package com.cosmin.licenta.workday.service;

import com.cosmin.licenta.workday.dto.response.MenuItemDTO;
import com.cosmin.licenta.workday.entity.Employee;
import com.cosmin.licenta.workday.entity.MenuItem;
import com.cosmin.licenta.workday.mapper.MenuItemMapper;
import com.cosmin.licenta.workday.repository.EmployeeRepository;
import com.cosmin.licenta.workday.repository.MenuItemRepository;
import com.cosmin.licenta.workday.util.RoleTypeReferentialEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.List;
import java.util.Optional;

@Service
public class MenuItemService {

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private MenuItemMapper menuItemMapper;

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<MenuItemDTO> getMenuItems(final Long employeeId) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (employeeOptional.isPresent()) {
            final Employee employee = employeeOptional.get();
            final List<MenuItem> menuItems = menuItemRepository.findAll();
            if (employee.getRoles().stream().anyMatch(role -> role.getLabel().equals(RoleTypeReferentialEnum.MANAGER.getLabel()))) {
                return menuItemMapper.entitiesToDomains(menuItems);
            } else if (employee.getRoles().stream().anyMatch(role -> role.getLabel().equals(RoleTypeReferentialEnum.ADMIN.getLabel()))) {
                //TODO
            } else {
                return menuItemMapper.entitiesToDomains(menuItems.subList(0, menuItems.size()-1));
            }
        }
        return null;
    }
}
