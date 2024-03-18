package com.abcgroep.crmsimulation.application.repositories;

import com.abcgroep.crmsimulation.application.entities.Timesheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {
    @Query("SELECT t FROM Timesheet t WHERE t.modifiedOn > :lastSyncTime")
    List<Timesheet> findModifiedSince(@Param("lastSyncTime") LocalDateTime lastSyncTime);
}
