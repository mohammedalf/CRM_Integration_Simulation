/*
package com.abcgroep.projectapi_simulation.integration.util.mapper.implementations;

import com.abcgroep.projectapi_simulation.application.entities.Consultant;
import com.abcgroep.projectapi_simulation.application.repositories.ConsultantRepository;

import com.abcgroep.projectapi_simulation.integration.util.mapper.interfaces.GenericMappingService;
import com.abcgroep.projectapi_simulation.integration.util.mapper.interfaces.MappingService;
import com.abcgroep.projectapi_simulation.integration.util.repositories.ExternalEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class ConsultantMappingService implements GenericMappingService<Consultant> {
    @Autowired
    @Qualifier("externalConsultantService")
    private ExternalEntityRepository externalConsultantRepository;

    @Autowired
    private ConsultantRepository consultantRepository;

    @Override
    public List<Consultant> mapStudents() {
        return null;
    }

    @Override
    public boolean areStudentsSaved() {
        return false;
    }



        @Override
    public List<Consultant> mapData(List<Map<String, Object>> rawData) {
        List<Map<String, Object>> rows = externalConsultantRepository.findAll();
        List<Consultant> consultants = new ArrayList<>();

        for (Map<String, Object> row : rows) {
            Consultant consultant = mapRowToConsultant(row);
            consultants.add(consultant);
        }

        if (!consultants.isEmpty()) {
            // Markeer de mapping als geïnitialiseerd

            return consultants;
        } else {
            // Mapping is geïnitialiseerd, maar er zijn geen consultants om te mappen
            return new ArrayList<>();
        }
    }
    private Consultant mapRowToConsultant(Map<String, Object> row) {
        Consultant consultant = new Consultant();
        consultant.setId((Long) row.get("id"));
        consultant.setName((String) row.get("name"));
        consultant.setEmail((String) row.get("email"));

        return consultant;
    }

    @Override
    public boolean isDataSaved() {
        return false;
    }
}
*/
