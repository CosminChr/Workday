package com.cosmin.licenta.workday.resource;

import com.cosmin.licenta.workday.dto.NotificationDTO;
import com.cosmin.licenta.workday.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<List<NotificationDTO>> getNotifications(@PathVariable(name = "employeeId") final Long employeeId) {
        return ResponseEntity.ok(notificationService.getNotificationsByEmployeeId(employeeId));
    }
}
