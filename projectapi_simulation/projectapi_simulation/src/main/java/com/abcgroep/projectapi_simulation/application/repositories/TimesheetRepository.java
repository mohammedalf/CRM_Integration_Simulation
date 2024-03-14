package com.abcgroep.projectapi_simulation.application.repositories;


import com.abcgroep.projectapi_simulation.application.entities.Timesheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {
    @Query("SELECT MAX(s.externalTimesheetId) FROM Timesheet s")
    Long findMaxExternalTimesheetId();
}
