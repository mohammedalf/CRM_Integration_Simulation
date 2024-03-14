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


import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TimesheetMappingService implements GenericMappingService<Timesheet> {
    @Autowired
    @Qualifier("externalTimesheetService")
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

    @Override
    public void saveAllMigratedData(List<Timesheet> timesheets) {
        for(Timesheet t : timesheets){
            logger.info("Timesheet met ID " + t.getId() + " wordt opgeslagen.");
        }
        timesheetRepository.saveAll(timesheets);
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
    public List<Timesheet> mapEntitysSinceLastId(Long lastMappedId) {
        List<Map<String, Object>> rows = externalTimesheetRepository.findSinceId(lastMappedId);

        if (rows.isEmpty()) {
            return Collections.emptyList();
        }
        return rows.stream()
                .map(TimesheetMapper::mapRowToTimesheet)
                .collect(Collectors.toList());
    }
}
