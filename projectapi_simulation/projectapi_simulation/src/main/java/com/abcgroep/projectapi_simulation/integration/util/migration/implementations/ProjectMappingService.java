package com.abcgroep.projectapi_simulation.integration.util.migration.implementations;




import com.abcgroep.projectapi_simulation.application.entities.Project;
import com.abcgroep.projectapi_simulation.application.repositories.ProjectRepository;
import com.abcgroep.projectapi_simulation.integration.crm.msdynamics.mappers.ProjectMapper;
import com.abcgroep.projectapi_simulation.integration.util.migration.interfaces.GenericMappingService;
import com.abcgroep.projectapi_simulation.integration.util.repositories.ExternalEntityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProjectMappingService implements GenericMappingService<Project> {

    @Autowired
    @Qualifier("externalProjectRepository")
    private ExternalEntityRepository externalProjectRepository;

    @Autowired
    private ProjectRepository projectRepository;
    private final Logger logger = LoggerFactory.getLogger(TimesheetMappingService.class);

    private boolean mappingInitialized = false;


    @Override
    public List<Project> mapData() {
        if (!mappingInitialized && !areEntitiesAlreadyMapped()) {
            List<Map<String, Object>> rows = externalProjectRepository.findAll();
            if (rows.isEmpty()) {
                return Collections.emptyList();
            }
            List<Project> projects = rows.stream()
                    .map(ProjectMapper::mapRowToProject)
                    .collect(Collectors.toList());
            mappingInitialized = true;
            return projects;
        } else {
            return Collections.emptyList();
        }
    }





    @Override
    public boolean areEntitiesAlreadyMapped() {
        boolean isProjectMigrated= projectRepository.count() > 0;
        if(isProjectMigrated){
            logger.info("Project-gegevens zijn reeds gemigreerd.");
        }
        return isProjectMigrated;
    }

/*@Override
public void saveAllMigratedData(List<Project> projects) {
    for(Project p : projects){
        logger.info("Timesheet met ID " + p.getExternalProjectId() + " wordt opgeslagen.");
    }
    projectRepository.saveAll(projects);
}*/

    @Override
    public void updateData(List<Project> projects) {
        for (Project project : projects) {
            Optional<Project> existingProject = projectRepository.findByExternalProjectId(project.getExternalProjectId());
            if (existingProject.isPresent()) {

                Project updatedProject = existingProject.get();
                updatedProject.setName(project.getName());
                updatedProject.setDescription(project.getDescription());
                updatedProject.setStartDate(project.getStartDate());
                updatedProject.setEndDate(project.getEndDate());

                logger.info("Project met ID " + updatedProject.getExternalProjectId() + " wordt bijgewerkt.");
                projectRepository.save(updatedProject);
            } else {

                projectRepository.save(project);
            }

        }
    }



    @Override
    public List<Project> mapEntitiesSinceLastModified(LocalDateTime lastModifiedTime) {
        Timestamp timestamp = Timestamp.valueOf(lastModifiedTime);
        List<Map<String, Object>> rows = externalProjectRepository.findModifiedSince(timestamp);
        if (rows != null){
            return rows.stream()
                    .map(ProjectMapper::mapRowToProject)
                    .collect(Collectors.toList());
        }
        else{
            return new ArrayList<>();
        }
    }
}
