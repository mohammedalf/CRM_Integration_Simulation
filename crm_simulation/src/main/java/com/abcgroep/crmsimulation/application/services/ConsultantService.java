package com.abcgroep.crmsimulation.application.services;

import com.abcgroep.crmsimulation.application.dtos.ConsultantDTO;
import com.abcgroep.crmsimulation.application.entities.Consultant;
import com.abcgroep.crmsimulation.application.repositories.ConsultantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ConsultantService {
    @Autowired
    private ConsultantRepository consultantRepository;

    public Consultant addConsultant(ConsultantDTO consultantDTO) {
        Consultant consultant = new Consultant();
        consultant.setName(consultantDTO.getName());
        consultant.setEmail(consultantDTO.getEmail());

        return consultantRepository.save(consultant);
    }

    public Consultant updateConsultant(Long id, ConsultantDTO consultantDTO) {
        Consultant consultant = consultantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consultant not found with id " + id));

        consultant.setName(consultantDTO.getName());
        consultant.setEmail(consultantDTO.getEmail());

        consultant.setModifiedOn(LocalDateTime.now());

        return consultantRepository.save(consultant);
    }
}
