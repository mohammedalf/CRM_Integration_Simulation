package com.abcgroep.projectapi_simulation.integration.util.setup;

import com.abcgroep.projectapi_simulation.integration.util.mapper.interfaces.GenericMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StartupDataMigrator {
    @Autowired
    private List<GenericMappingService<?>> mappingServices;

    @EventListener(ApplicationReadyEvent.class)
    public void migrateDataOnStartup() {
        for (GenericMappingService<?> service : mappingServices) {
            migrateServiceData(service);
        }
    }

    @SuppressWarnings("unchecked")
    private <T> void migrateServiceData(GenericMappingService<T> service) {
        List<T> migratedData = service.mapData();
        if (!migratedData.isEmpty()) {
            service.saveAllMigratedData(migratedData);
        }
    }
}
