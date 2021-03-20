package com.onemedics.pushapi.config;

import javax.sql.DataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LiquibaseConfiguration {
    @Bean
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public SpringLiquibase liquibase(
            DataSource dataSource, LiquibaseProperties liquibaseProperties) {
        // Use liquibase.integration.spring.SpringLiquibase if you don't want Liquibase to start
        // asynchronously
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog(liquibaseProperties.getChangeLog());
        liquibase.setContexts(liquibaseProperties.getContexts());
        liquibase.setDefaultSchema(liquibaseProperties.getDefaultSchema());
        liquibase.setDropFirst(liquibaseProperties.isDropFirst());
        liquibase.setChangeLogParameters(liquibaseProperties.getParameters());
        liquibase.setShouldRun(liquibaseProperties.isEnabled());
        return liquibase;
    }
}
