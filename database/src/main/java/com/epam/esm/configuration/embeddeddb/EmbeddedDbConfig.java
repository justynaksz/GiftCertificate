package com.epam.esm.configuration.embeddeddb;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/**
 * Embedded database configuration class.
 */
public class EmbeddedDbConfig {

    /**
     * Creates embedded database of H2 type.
     * @return database     embedded database of H2 type
     */
    @Bean
    public static EmbeddedDatabase createH2DataSource() {
        EmbeddedDatabase dataBase = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("dbEntities.sql")
                .addScript("dbRecords.sql")
                .build();
        return dataBase;
    }
}
