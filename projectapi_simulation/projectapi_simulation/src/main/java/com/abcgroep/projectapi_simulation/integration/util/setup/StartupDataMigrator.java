package com.abcgroep.projectapi_simulation.integration.util.setup;

import com.abcgroep.projectapi_simulation.application.entities.Consultant;
import com.abcgroep.projectapi_simulation.application.repositories.ConsultantRepository;
import com.abcgroep.projectapi_simulation.integration.util.mapper.implementations.ConsultantMappingServiceImpl;
import com.abcgroep.projectapi_simulation.integration.util.mapper.interfaces.GenericMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StartupDataMigrator{


    private final ConsultantMappingServiceImpl consultantMappingService;
    private final ConsultantRepository studentRepository;
    @Autowired
    public StartupDataMigrator(ConsultantMappingServiceImpl consultantMappingService, ConsultantRepository studentRepository) {
        this.consultantMappingService = consultantMappingService;
        this.studentRepository = studentRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void migrateDataOnStartup() {
        List<Consultant> migratedStudents = consultantMappingService.mapData();
        saveAllMigratedConsultants(migratedStudents);


    }

    private void saveAllMigratedConsultants(List<Consultant> migratedStudents){
        studentRepository.saveAll(migratedStudents);
    }
}
