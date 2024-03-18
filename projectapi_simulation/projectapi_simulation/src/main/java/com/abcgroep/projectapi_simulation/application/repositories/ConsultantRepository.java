package com.abcgroep.projectapi_simulation.application.repositories;


import com.abcgroep.projectapi_simulation.application.entities.Consultant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface ConsultantRepository extends JpaRepository<Consultant, Long> {

    @Query("SELECT MAX(s.externalConsultantId) FROM Consultant s")
    Long findMaxExternalConsultantId();

    Optional<Consultant> findByExternalConsultantId(Long externalConsultantId);

}
