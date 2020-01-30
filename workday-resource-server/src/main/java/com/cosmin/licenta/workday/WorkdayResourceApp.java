package com.cosmin.licenta.workday;

import com.cosmin.licenta.workday.config.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WorkdayResourceApp {

    public static void main(String[] args) {
        SpringApplication.run(new Class[] { WorkdayResourceApp.class, SecurityConfig.class, }, args);
    }
}
