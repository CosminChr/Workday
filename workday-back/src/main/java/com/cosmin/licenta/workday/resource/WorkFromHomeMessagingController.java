package com.cosmin.licenta.workday.resource;

import com.cosmin.licenta.workday.dto.WorkFromHomeDTO;
import com.cosmin.licenta.workday.service.WorkFromHomeService;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WorkFromHomeMessagingController {

    private final WorkFromHomeService workFromHomeService;

    public WorkFromHomeMessagingController(WorkFromHomeService workFromHomeService) {
        this.workFromHomeService = workFromHomeService;
    }

    @MessageMapping("/employee/workFromHome")
    @SendTo("/topic/manager/workFromHome")
    public ResponseEntity<List<WorkFromHomeDTO>> sendWorkFromHomeRequest(final String managerId) {
        return ResponseEntity.ok(workFromHomeService.getWorkFromHomeHandledByManager(Long.parseLong(managerId)));
    }

    @MessageMapping("/manager/workFromHome")
    @SendTo("/topic/employee/workFromHome")
    public ResponseEntity<WorkFromHomeDTO> handleWorkFromHomeRequest(final String workFromHomeId) {
        return ResponseEntity.ok(workFromHomeService.getWorkFromHome(Long.parseLong(workFromHomeId)));
    }

}
