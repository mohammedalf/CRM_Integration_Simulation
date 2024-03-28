package com.abcgroep.projectapi_simulation.integration.crm.msdynamics.external.repositories;

import com.abcgroep.projectapi_simulation.integration.util.repositories.ExternalEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Service
public class ExternalProjectRepository implements ExternalEntityRepository {
    private final JdbcTemplate jdbcTemplate;
    private final String schema;

    @Autowired
    public ExternalProjectRepository(JdbcTemplate jdbcTemplate, @Value("${extern.datasource.schema}") String schema) {
        this.jdbcTemplate = jdbcTemplate;
        this.schema = schema;
    }

    @Override
    public List<Map<String, Object>> findAll() {
        String sql = String.format("SELECT * FROM %s.project", schema);
        return jdbcTemplate.queryForList(sql);
    }

/*
    @Override
    public List<Map<String, Object>> findSinceId(Long lastMappedId) {
        String sql = String.format("SELECT * FROM %s.project WHERE id > ?", this.schema);
        return jdbcTemplate.queryForList(sql, lastMappedId);
    }
*/

    @Override
    public List<Map<String, Object>> findModifiedSince(Timestamp lastModifiedTime) {
        String sql = String.format("SELECT * FROM %s.project WHERE modified_on > ?", this.schema);
        return jdbcTemplate.queryForList(sql, lastModifiedTime);
    }


    // In ExternalProjectRepository
    public List<Map<String, Object>> findProjectConsultantRelationships() {
        String sql = String.format("SELECT project_id, consultant_id FROM %s.project_consultant", this.schema);
        return jdbcTemplate.queryForList(sql);
    }

}
