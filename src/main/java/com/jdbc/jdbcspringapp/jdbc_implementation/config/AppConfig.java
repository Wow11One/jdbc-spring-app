package com.jdbc.jdbcspringapp.jdbc_implementation.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

    @Bean
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url("jdbc:postgresql://localhost:5432/football_club");
        dataSourceBuilder.username("postgres");
        dataSourceBuilder.password("Reserved2003");
        return dataSourceBuilder.build();
    }
}
