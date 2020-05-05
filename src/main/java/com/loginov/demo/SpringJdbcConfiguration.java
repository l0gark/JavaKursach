package com.loginov.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.loginov.demo")
public class SpringJdbcConfiguration {
    @Bean
    public DataSource mysqlDataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/ABDULLA");
        dataSource.setUsername("l0gark");
        dataSource.setPassword("");

        return dataSource;
    }
}
