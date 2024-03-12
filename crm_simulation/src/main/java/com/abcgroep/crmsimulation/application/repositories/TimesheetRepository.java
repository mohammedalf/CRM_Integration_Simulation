package com.abcgroep.crmsimulation.application.repositories;

import com.abcgroep.crmsimulation.application.entities.Timesheet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {
}
