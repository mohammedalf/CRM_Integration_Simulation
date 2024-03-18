package com.abcgroep.projectapi_simulation.integration.util.sync;

import com.abcgroep.projectapi_simulation.application.entities.Consultant;
import com.abcgroep.projectapi_simulation.application.entities.Project;
import com.abcgroep.projectapi_simulation.application.entities.Timesheet;
import com.abcgroep.projectapi_simulation.application.repositories.ConsultantRepository;
import com.abcgroep.projectapi_simulation.application.repositories.ProjectRepository;
import com.abcgroep.projectapi_simulation.application.repositories.TimesheetRepository;
import com.abcgroep.projectapi_simulation.integration.util.migration.interfaces.GenericMappingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.time.LocalDateTime;
import java.util.List;

@Component
public class PeriodicDataSync {
    private final Logger logger = LoggerFactory.getLogger(PeriodicDataSync.class);
    private final GenericMappingService<Project> projectMappingService;
    private final GenericMappingService<Consultant> consultantMappingService;
    private final GenericMappingService<Timesheet> timesheetMappingService;

    private final ProjectRepository projectRepository;
    private final TimesheetRepository timesheetRepository;
    private final ConsultantRepository consultantRepository;
    @Autowired
    public PeriodicDataSync(GenericMappingService<Project> projectMappingService, GenericMappingService<Consultant> consultantMappingService, GenericMappingService<Timesheet> timesheetMappingService, ProjectRepository projectRepository, TimesheetRepository timesheetRepository, ConsultantRepository consultantRepository) {
        this.projectMappingService = projectMappingService;
        this.consultantMappingService = consultantMappingService;
        this.timesheetMappingService = timesheetMappingService;
        this.projectRepository = projectRepository;
        this.timesheetRepository = timesheetRepository;
        this.consultantRepository = consultantRepository;
    }



    public void syncData() {
        logger.info("Starting periodic data synchronization...");
        syncConsultantData();
        syncProjectData();
        syncTimesheetData();
    }


    private void syncConsultantData() {
        syncGenericData(consultantMappingService, "Consultant");
    }

    private void syncProjectData() {
        syncGenericData(projectMappingService, "Project");
    }

    private void syncTimesheetData() {
        syncGenericData(timesheetMappingService, "Timesheet");
    }




    private <T> void syncGenericData(GenericMappingService<T> mappingService, String entityName) {
        LocalDateTime lastModifiedTime = SyncTimestampManager.getLastModifiedTime(entityName);
        logger.info("Last modified time for {}: {}", entityName, lastModifiedTime);

        List<T> newOrUpdatedEntities = mappingService.mapEntitiesSinceLastModified(lastModifiedTime);
        if (!newOrUpdatedEntities.isEmpty()) {
            mappingService.updateData(newOrUpdatedEntities);
            logger.info("{} new or updated {} entities have been synchronized and saved.", newOrUpdatedEntities.size(), entityName);
            SyncTimestampManager.updateLastModifiedTime(entityName, LocalDateTime.now());
        } else {
            logger.info("No new or updated {} entities to synchronize based on last modified time.", entityName);
        }
    }


}
