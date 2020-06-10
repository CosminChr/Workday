package com.cosmin.licenta.workday.service;

import com.cosmin.licenta.workday.dto.LanguageDTO;
import com.cosmin.licenta.workday.dto.NotificationDTO;
import com.cosmin.licenta.workday.entity.Employee;
import com.cosmin.licenta.workday.entity.Notification;
import com.cosmin.licenta.workday.mapper.NotificationMapper;
import com.cosmin.licenta.workday.repository.EmployeeRepository;
import com.cosmin.licenta.workday.repository.NotificationRepository;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    private final NotificationMapper notificationMapper;

    private final EmployeeRepository employeeRepository;


    public NotificationService(NotificationRepository notificationRepository, NotificationMapper notificationMapper, EmployeeRepository employeeRepository) {
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public List<NotificationDTO> getNotificationsByEmployeeId(final Long employeeId) {

        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (employeeOptional.isPresent()) {
            Optional<List<Notification>> notificationsOptional = notificationRepository.findByEmployee(employeeOptional.get());
            if (notificationsOptional.isPresent()) {
                return notificationMapper.entitiesToDomains(notificationsOptional.get());
            }
        }
        return null;
    }


}
