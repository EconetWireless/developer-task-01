package com.econetwireless.epay.dao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * Created by tnyamakura on 29/11/2016.
 */
@Configuration
@PropertySource("classpath:epay-jdbc.properties")
public class DataSourceConfiguration {

    @Resource
    private Environment env;

    @Bean
    public DataSource dataSource() {
        final EmbeddedDatabaseBuilder databaseBuilder = new EmbeddedDatabaseBuilder();
        databaseBuilder.setName(env.getRequiredProperty("epay.jdbc.dbname"));
        databaseBuilder.setType(EmbeddedDatabaseType.HSQL);
        return databaseBuilder.build();
    }
}
