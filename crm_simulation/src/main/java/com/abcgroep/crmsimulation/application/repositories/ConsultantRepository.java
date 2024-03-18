package com.abcgroep.crmsimulation.application.repositories;

import com.abcgroep.crmsimulation.application.entities.Consultant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface ConsultantRepository extends JpaRepository<Consultant, Long> {
    @Query("SELECT c FROM Consultant c WHERE c.modifiedOn > :lastSyncTime")
    List<Consultant> findModifiedSince(@Param("lastSyncTime") LocalDateTime lastSyncTime);
}
