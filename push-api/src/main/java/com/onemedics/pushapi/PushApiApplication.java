package com.onemedics.pushapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@SpringBootApplication
@EnableConfigurationProperties({LiquibaseProperties.class})
public class PushApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(PushApiApplication.class, args);
    }

    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }
}
