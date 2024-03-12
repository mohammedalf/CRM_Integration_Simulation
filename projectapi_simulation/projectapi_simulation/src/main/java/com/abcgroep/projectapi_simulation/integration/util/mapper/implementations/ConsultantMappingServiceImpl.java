package com.abcgroep.projectapi_simulation.integration.util.mapper.implementations;

import com.abcgroep.projectapi_simulation.application.entities.Consultant;
import com.abcgroep.projectapi_simulation.application.repositories.ConsultantRepository;
import com.abcgroep.projectapi_simulation.integration.util.mapper.interfaces.GenericMappingService;
import com.abcgroep.projectapi_simulation.integration.util.repositories.ExternalEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ConsultantMappingServiceImpl implements GenericMappingService<Consultant> {
    @Autowired
    @Qualifier("externalConsultantService")

    private ExternalEntityRepository externalConsultantRepository;
    @Autowired
    private ConsultantRepository consultantRepository;
    private boolean mappingInitialized = false;



    @Override
    public List<Consultant> mapData() {
        if (!mappingInitialized && !areEntitiesAlreadyMapped()) {
            List<Map<String, Object>> rows = externalConsultantRepository.findAll();
            if (rows.isEmpty()) {
                return Collections.emptyList();
            }
            List<Consultant> consultants = rows.stream()
                    .map(this::mapRowToConsultant)
                    .collect(Collectors.toList());
            mappingInitialized = true;
            return consultants;
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public boolean areEntitiesAlreadyMapped() {
        return consultantRepository.count() > 0;
    }


    @Override
    public boolean isDataSaved() {
        return false;
    }

    private Consultant mapRowToConsultant(Map<String, Object> row) {
        Consultant consultant = new Consultant();
        consultant.setId((Long) row.get("id"));
        consultant.setName((String) row.get("name"));
        consultant.setEmail((String) row.get("email"));
        return consultant;
    }
}
