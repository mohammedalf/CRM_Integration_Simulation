package com.abcgroep.projectapi_simulation.integration.crm.msdynamics.mappers;

import com.abcgroep.projectapi_simulation.application.entities.Project;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class ProjectMapper {
    public static Project mapRowToProject(Map<String, Object> row) {
        Project project = new Project();
        project.setExternalProjectId((Long) row.get("id"));
        project.setName((String) row.get("name"));
        project.setDescription((String) row.get("description"));
        project.setLastModified((LocalDateTime) row.get("modified_on"));

        java.sql.Date startDateSql = (java.sql.Date) row.get("start_date");
        if (startDateSql != null) {
            LocalDate startDate = startDateSql.toLocalDate();
            project.setStartDate(startDate);
        }

        java.sql.Date endDateSql = (java.sql.Date) row.get("end_date");
        if (endDateSql != null) {
            LocalDate endDate = endDateSql.toLocalDate();
            project.setEndDate(endDate);
        }

        return project;
    }



}
