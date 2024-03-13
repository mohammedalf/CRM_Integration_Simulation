package com.abcgroep.projectapi_simulation.integration.util.mapper.implementations;



import com.abcgroep.projectapi_simulation.application.entities.Project;
import com.abcgroep.projectapi_simulation.application.repositories.ProjectRepository;
import com.abcgroep.projectapi_simulation.integration.crm.msdynamics.mappers.ProjectMapper;
import com.abcgroep.projectapi_simulation.integration.util.mapper.interfaces.GenericMappingService;
import com.abcgroep.projectapi_simulation.integration.util.repositories.ExternalEntityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProjectMappingService implements GenericMappingService<Project> {

    @Autowired
    @Qualifier("externalProjectService")
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

    @Override
    public void saveAllMigratedData(List<Project> projects) {
        for(Project p : projects){
            logger.info("Timesheet met ID " + p.getId() + " wordt opgeslagen.");
        }
        projectRepository.saveAll(projects);
    }



}
