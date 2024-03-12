package com.abcgroep.crmsimulation.application.services;

import com.abcgroep.crmsimulation.application.dtos.ConsultantDTO;
import com.abcgroep.crmsimulation.application.entities.Consultant;
import com.abcgroep.crmsimulation.application.repositories.ConsultantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultantService {
    @Autowired
    private ConsultantRepository consultantRepository;

    public Consultant addConsultant(ConsultantDTO consultantDTO) {
        // Converteer ConsultantDTO naar Consultant entiteit
        Consultant consultant = new Consultant();
        consultant.setName(consultantDTO.getName());
        consultant.setEmail(consultantDTO.getEmail());

        // Opslaan en teruggeven van de Consultant entiteit
        return consultantRepository.save(consultant);
    }
}
