package com.abcgroep.projectapi_simulation.integration.util.migration.implementations;

import com.abcgroep.projectapi_simulation.application.entities.Consultant;
import com.abcgroep.projectapi_simulation.application.repositories.ConsultantRepository;
import com.abcgroep.projectapi_simulation.integration.crm.msdynamics.mappers.ConsultantMapper;
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
public class ConsultantMappingService implements GenericMappingService<Consultant> {
    @Autowired
    @Qualifier("externalConsultantService")

    private ExternalEntityRepository externalConsultantRepository;
    @Autowired
    private ConsultantRepository consultantRepository;
    private final Logger logger = LoggerFactory.getLogger(TimesheetMappingService.class);
    private boolean mappingInitialized = false;



    @Override
    public List<Consultant> mapData() {
        if (!mappingInitialized && !areEntitiesAlreadyMapped()) {
            List<Map<String, Object>> rows = externalConsultantRepository.findAll();
            if (rows.isEmpty()) {
                return Collections.emptyList();
            }
            List<Consultant> consultants = rows.stream()
                    .map(ConsultantMapper::mapRowToConsultant)
                    .collect(Collectors.toList());
            mappingInitialized = true;
            return consultants;
        } else {
            return Collections.emptyList();
        }
    }


    @Override
    public boolean areEntitiesAlreadyMapped() {
        boolean isConsultantMigrated =  consultantRepository.count() > 0;
        if(isConsultantMigrated){
            logger.info("Consultant-gegevens zijn reeds gemigreerd.");
        }
        return isConsultantMigrated;
    }





    @Override
    public void saveAllMigratedData(List<Consultant> consultants) {
        for(Consultant c : consultants){
            logger.info("Consultant met ID " + c.getId() + " wordt opgeslagen.");
        }
        consultantRepository.saveAll(consultants);
    }

    @Override
    public List<Consultant> mapEntitysSinceLastId(Long lastMappedId) {
        // Roep de externe repository aan om alleen nieuwe gegevens op te halen die na lastMappedId zijn toegevoegd
//        List<Map<String, Object>> rows = externalConsultantRepository.findSinceId(lastMappedId);

        // Map de opgehaalde gegevens naar StudentDTO's
//        List<Consultant> dtos = mapToDTOs(rows);

        // Map de DTO's naar Student-entiteiten
//        return mapDTOsToStudents(dtos);
        List<Map<String, Object>> rows = externalConsultantRepository.findSinceId(lastMappedId);

        if (rows.isEmpty()) {
            return Collections.emptyList();
        }

        return rows.stream()
                .map(ConsultantMapper::mapRowToConsultant)
                .collect(Collectors.toList());
    }
}