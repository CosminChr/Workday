package com.cosmin.licenta.workday;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackageClasses ={WorkdayApp.class})
@SpringBootApplication
public class WorkdayApp {

    public static void main(String[] args) {
        SpringApplication.run(WorkdayApp.class, args);
    }
}
