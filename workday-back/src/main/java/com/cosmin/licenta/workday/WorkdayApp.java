package com.cosmin.licenta.workday;

import com.cosmin.licenta.workday.entity.Employee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EntityScan(basePackageClasses = {Employee.class})
@EnableTransactionManagement
@EnableJpaRepositories("com.cosmin.licenta.workday.repository")
@EnableConfigurationProperties(LiquibaseProperties.class)
@SpringBootApplication
public class WorkdayApp {
    public static void main(String[] args) {
        SpringApplication.run(WorkdayApp.class, args);
    }
}
