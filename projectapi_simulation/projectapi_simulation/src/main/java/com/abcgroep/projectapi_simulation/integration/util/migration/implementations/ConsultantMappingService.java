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

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;



@Service
public class ConsultantMappingService implements GenericMappingService<Consultant> {
    @Autowired
    @Qualifier("externalConsultantRepository")

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




/*@Override
public void saveAllMigratedData(List<Consultant> consultants) {
    for(Consultant c : consultants){
        logger.info("Consultant met ID " + c.getExternalConsultantId() + " wordt opgeslagen.");
    }
    consultantRepository.saveAll(consultants);
}*/

    @Override
    public void updateData(List<Consultant> consultants) {
        for (Consultant consultant : consultants) {
            Optional<Consultant> existingConsultant = consultantRepository.findByExternalConsultantId(consultant.getExternalConsultantId());
            if (existingConsultant.isPresent()) {

                Consultant updatedConsultant = existingConsultant.get();
                updatedConsultant.setName(consultant.getName());
                updatedConsultant.setEmail(consultant.getEmail());

                logger.info("Consultant met ID " + updatedConsultant.getExternalConsultantId() + " wordt bijgewerkt.");
                consultantRepository.save(updatedConsultant);
            } else {
                consultantRepository.save(consultant);
            }

    }

    }



    @Override
    public List<Consultant> mapEntitiesSinceLastModified(LocalDateTime lastModifiedTime) {
        Timestamp timestamp = Timestamp.valueOf(lastModifiedTime);
        List<Map<String, Object>> rows = externalConsultantRepository.findModifiedSince(timestamp);
        if (rows != null) {
            return rows.stream()
                    .map(ConsultantMapper::mapRowToConsultant)
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

}
