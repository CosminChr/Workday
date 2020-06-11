package com.cosmin.licenta.workday.resource;

import com.cosmin.licenta.workday.dto.HolidayDTO;
import com.cosmin.licenta.workday.service.HolidayService;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HolidayMessagingController {

    private HolidayService holidayService;

    public HolidayMessagingController(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @MessageMapping("/employee")
    @SendTo("/topic/manager")
    public ResponseEntity<List<HolidayDTO>> sendHolidayRequest(final String managerId) {
        return ResponseEntity.ok(holidayService.getHolidaysHandledByManager(Long.parseLong(managerId)));
    }

    @MessageMapping("/manager")
    @SendTo("/topic/employee")
    public ResponseEntity<HolidayDTO> handleHolidayRequest(final String holidayId) {
        return ResponseEntity.ok(holidayService.getHoliday(Long.parseLong(holidayId)));
    }
}


