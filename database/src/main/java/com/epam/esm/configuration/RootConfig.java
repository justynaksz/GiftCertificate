package com.epam.esm.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Data source configuration class.
 */
@Configuration
@Profile("prod")
@EnableTransactionManagement
@ComponentScan("com.epam.esm")
@PropertySource(value={"classpath:jdbc.properties"})
public class RootConfig {

    @Autowired
    private Environment environment;

    @Autowired
    public RootConfig(Environment environment) {
        this.environment = environment;
    }

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

    /**
     * Return the context for the application.
     * @ return AnnotationConfigApplicationContext()
     */
    @Bean
    public ApplicationContext getApplicationContext() {
        return new AnnotationConfigApplicationContext();
    }
}
