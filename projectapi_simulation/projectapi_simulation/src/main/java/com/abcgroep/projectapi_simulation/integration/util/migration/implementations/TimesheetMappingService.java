package com.abcgroep.projectapi_simulation.integration.util.migration.implementations;


import com.abcgroep.projectapi_simulation.application.entities.Timesheet;
import com.abcgroep.projectapi_simulation.application.repositories.TimesheetRepository;
import com.abcgroep.projectapi_simulation.integration.crm.msdynamics.mappers.TimesheetMapper;
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
public class TimesheetMappingService implements GenericMappingService<Timesheet> {
    @Autowired
    @Qualifier("externalTimesheetRepository")
    private ExternalEntityRepository externalTimesheetRepository;

    @Autowired
    private TimesheetRepository timesheetRepository;

    private boolean mappingInitialized = false;
    private final Logger logger = LoggerFactory.getLogger(TimesheetMappingService.class);

    @Override
    public List<Timesheet> mapData() {
        if (!mappingInitialized && !areEntitiesAlreadyMapped()) {
            List<Map<String, Object>> rows = externalTimesheetRepository.findAll();
            if (rows.isEmpty()) {
                return Collections.emptyList();
            }
            List<Timesheet> timesheets = rows.stream()
                    .map(TimesheetMapper::mapRowToTimesheet)
                    .collect(Collectors.toList());
            mappingInitialized = true;
            return timesheets;
        } else {
            return Collections.emptyList();
        }
    }

/*    @Override
    public void saveAllMigratedData(List<Timesheet> timesheets) {
        for(Timesheet t : timesheets){
            logger.info("Timesheet met ID " + t.getExternalTimesheetId() + " wordt opgeslagen.");
        }
        timesheetRepository.saveAll(timesheets);
    }*/

    @Override
    public void updateData(List<Timesheet> timesheets) {
        for (Timesheet timesheet : timesheets) {
            Optional<Timesheet> existingTimesheet = timesheetRepository.findByExternalTimesheetId(timesheet.getExternalTimesheetId());
            if (existingTimesheet.isPresent()) {

                Timesheet updatedTimesheet = existingTimesheet.get();
                updatedTimesheet.setDate(timesheet.getDate());
                updatedTimesheet.setHours(timesheet.getHours());
                updatedTimesheet.setDescription(timesheet.getDescription());

                logger.info("Timesheet met ID " + updatedTimesheet.getExternalTimesheetId() + " wordt bijgewerkt.");
                timesheetRepository.save(updatedTimesheet);
            } else {
                timesheetRepository.save(timesheet);
            }

        }
    }

    @Override
    public boolean areEntitiesAlreadyMapped() {
        boolean isTimesheetMigrated = timesheetRepository.count() >0;
        if(isTimesheetMigrated){
            logger.info("Timesheet-gegevens zijn reeds gemigreerd.");

        }
        return isTimesheetMigrated;
    }



    @Override
    public List<Timesheet> mapEntitiesSinceLastModified(LocalDateTime lastModifiedTime) {
        Timestamp timestamp = Timestamp.valueOf(lastModifiedTime);
        List<Map<String, Object>> rows = externalTimesheetRepository.findModifiedSince(timestamp);
        if (rows != null) {
            return rows.stream()
                    .map(TimesheetMapper::mapRowToTimesheet)
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }
}
