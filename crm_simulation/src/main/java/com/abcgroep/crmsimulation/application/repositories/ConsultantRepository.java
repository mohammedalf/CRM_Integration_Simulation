package com.abcgroep.crmsimulation.application.repositories;

import com.abcgroep.crmsimulation.application.entities.Consultant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultantRepository extends JpaRepository<Consultant, Long> {

}
