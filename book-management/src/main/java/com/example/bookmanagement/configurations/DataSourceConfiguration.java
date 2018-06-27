package com.example.bookmanagement.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {

    @Bean("DataSource")
    @Profile("pgsql")
    public DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();

        driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
        driverManagerDataSource.setUrl("jdbc:postgresql://localhost:5432/pp_class");
        driverManagerDataSource.setUsername("postgres");
        driverManagerDataSource.setPassword("root");

        return  driverManagerDataSource;
    }
    @Bean("DataSource")
    @Profile("h2")
    public DataSource forh2(){
        EmbeddedDatabaseBuilder databaseBuilder = new EmbeddedDatabaseBuilder();
        databaseBuilder.setType(EmbeddedDatabaseType.H2);

        databaseBuilder.addScript("database/schema.sql");
        databaseBuilder.addScript("database/data.sql");
        return databaseBuilder.build();
    }


}
