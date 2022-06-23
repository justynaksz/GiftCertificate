package com.epam.esm.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Data source configuration class.
 */
@Configuration
@ComponentScan(basePackages = {"com.epam.esm.DAOImpl"})
@PropertySource(value={"classpath:jdbc.properties"})
public class RootConfig {

    @Autowired
    private Environment environment;

    /**
     * Defines data source with properties specified in jdbc.properties file.
     * @ return dataSource    configured dataSource
     */
    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername((environment.getRequiredProperty("jdbc.username")));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return dataSource;
    }
}
