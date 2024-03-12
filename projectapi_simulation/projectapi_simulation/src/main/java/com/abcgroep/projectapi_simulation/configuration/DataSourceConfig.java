package com.abcgroep.projectapi_simulation.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class DataSourceConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    @Primary
    public DataSource eigenDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "extern.datasource")
    public DataSource externDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public JdbcTemplate externJdbcTemplate(@Qualifier("externDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
