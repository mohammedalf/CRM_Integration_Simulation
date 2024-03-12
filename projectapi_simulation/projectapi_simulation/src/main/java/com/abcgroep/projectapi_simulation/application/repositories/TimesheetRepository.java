package com.abcgroep.projectapi_simulation.application.repositories;


import com.abcgroep.projectapi_simulation.application.entities.Timesheet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {
}
