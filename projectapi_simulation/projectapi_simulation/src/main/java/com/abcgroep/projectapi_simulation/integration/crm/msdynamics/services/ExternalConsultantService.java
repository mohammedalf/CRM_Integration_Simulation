package com.abcgroep.projectapi_simulation.integration.crm.msdynamics.services;

import com.abcgroep.projectapi_simulation.integration.util.repositories.ExternalEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ExternalConsultantService implements ExternalEntityRepository {
    private final JdbcTemplate jdbcTemplate;
    private final String schema;

    @Autowired
    public ExternalConsultantService(JdbcTemplate jdbcTemplate, @Value("${extern.datasource.schema}") String schema) {
        this.jdbcTemplate = jdbcTemplate;
        this.schema = schema;
    }

    @Override
    public List<Map<String, Object>> findAll() {
        String sql = String.format("SELECT * FROM %s.consultant", this.schema);
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public List<Map<String, Object>> findSinceId(Long lastMappedId) {
        String sql = String.format("SELECT * FROM %s.consultant WHERE id > ?", this.schema);
        return jdbcTemplate.queryForList(sql, lastMappedId);
    }
}
