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
        syncGenericData(consultantMappingService, consultantRepository.findMaxExternalConsultantId(), "Consultant");
    }

    private void syncProjectData() {
        syncGenericData(projectMappingService, projectRepository.findMaxExternalProjectId(), "Project");
    }

    private void syncTimesheetData() {
        syncGenericData(timesheetMappingService, timesheetRepository.findMaxExternalTimesheetId(), "Timesheet");
    }

    private <T> void syncGenericData(GenericMappingService<T> mappingService, Long lastMappedId, String entityName) {
        logger.info("Last mapped external ID for {}: {}", entityName, lastMappedId);

        List<T> newEntities = mappingService.mapEntitysSinceLastId(lastMappedId);
        if (!newEntities.isEmpty()) {
            mappingService.saveAllMigratedData(newEntities);
            logger.info("{} new {} entities have been synchronized and saved.", newEntities.size(), entityName);
        } else {
            logger.info("No new {} entities to synchronize.", entityName);
        }
    }




















/*
    public void syncConsultantData() {
        logger.info("Periodieke synchronisatie gestart...");
        Long lastMappedExternalId = consultantRepository.findMaxExternalConsultantId();
        logger.info("Laatste gemapte externe consultant-ID: {}", lastMappedExternalId);

        //nieuwe studenten
        List<Consultant> newConsultants = consultantMappingService.mapEntitysSinceLastId(lastMappedExternalId);
//        logger.info("Aantal nieuwe studenten opgehaald: {}", newConsultants.size());
        if (!newConsultants.isEmpty()) {
            consultantMappingService.saveAllMigratedData(newConsultants);
            logger.info("Synchronized and saved {} new Consultants.", newConsultants.size());
        } else {
            logger.info("No new Consultants to synchronize.");
        }

    }

    public void syncData() {
        syncConsultantData();

    }*/

/*    private <T> void syncEntity(GenericMappingService<T> mappingService) {
        if (!mappingService.areEntitiesAlreadyMapped()) {
            List<T> entities = mappingService.mapData();
            mappingService.saveAllMigratedData(entities);
            logger.info("{} entities synchronized.", entities.getClass().getSimpleName()); // This line may need adjustment
        }
    }*/
}
