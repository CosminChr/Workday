package com.cosmin.licenta.workday.resource;

import com.cosmin.licenta.workday.dto.HolidayDTO;
import com.cosmin.licenta.workday.dto.OvertimeDTO;
import com.cosmin.licenta.workday.service.HolidayService;
import com.cosmin.licenta.workday.service.OvertimeService;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OvertimeMessagingController {

    private OvertimeService overtimeService;

    public OvertimeMessagingController(OvertimeService overtimeService) {
        this.overtimeService = overtimeService;
    }

    @MessageMapping("/employee/overtime")
    @SendTo("/topic/manager/overtime")
    public ResponseEntity<List<OvertimeDTO>> sendOvertimeRequest(final String managerId) {
        return ResponseEntity.ok(overtimeService.getOvertimeHandledByManager(Long.parseLong(managerId)));
    }

    @MessageMapping("/manager/overtime")
    @SendTo("/topic/employee/overtime")
    public ResponseEntity<OvertimeDTO> handleOvertimeRequest(final String holidayId) {
        return ResponseEntity.ok(overtimeService.getOvertime(Long.parseLong(holidayId)));
    }
}
