package com.abcgroep.projectapi_simulation.application.repositories;


import com.abcgroep.projectapi_simulation.application.entities.Timesheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {
    @Query("SELECT MAX(s.externalTimesheetId) FROM Timesheet s")
    Long findMaxExternalTimesheetId();

    Optional<Timesheet> findByExternalTimesheetId(Long externalTimesheetId);
}
