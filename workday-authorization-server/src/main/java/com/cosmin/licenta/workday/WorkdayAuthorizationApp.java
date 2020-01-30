package com.cosmin.licenta.workday;

import com.cosmin.licenta.workday.com.cosmin.licenta.workday.config.AuthorizationServerConfig;
import com.cosmin.licenta.workday.com.cosmin.licenta.workday.config.WorkdaySecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WorkdayAuthorizationApp {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(new Class[] { WorkdayAuthorizationApp.class, AuthorizationServerConfig.class, WorkdaySecurityConfig.class }, args);
    }

}
