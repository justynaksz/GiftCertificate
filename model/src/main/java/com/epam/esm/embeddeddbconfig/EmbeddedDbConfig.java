package com.epam.esm.embeddeddbconfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/**
 * Embedded database configuration class.
 */
@Configuration
@ComponentScan("com.epam.esm")
@Profile("dev")
public class EmbeddedDbConfig {

    /**
     * Creates embedded database of H2 type.
     * @return database     embedded database of H2 type
     */
    @Bean
    public static DataSource createH2DataSource() {
        DataSource dataBase = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("dbEntities.sql")
                .addScript("dbRecords.sql")
                .build();
        return dataBase;
    }

    /**
     * Return the context for the application.
     * @ return AnnotationConfigApplicationContext()
     */
    @Bean
    public ApplicationContext getApplicationContext() {
        return new AnnotationConfigApplicationContext();
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }
}
