package com.abcgroep.projectapi_simulation.integration.crm.msdynamics.mappers;

import com.abcgroep.projectapi_simulation.application.entities.Consultant;

import java.util.Map;

public class ConsultantMapper {
    public static Consultant mapRowToConsultant(Map<String, Object> row) {
        Consultant consultant = new Consultant();
        consultant.setExternalConsultantId((Long) row.get("id"));
        consultant.setName((String) row.get("name"));
        consultant.setEmail((String) row.get("email"));
        return consultant;
    }

}
