package com.abcgroep.crmsimulation.application.controllers;

import com.abcgroep.crmsimulation.application.dtos.TimesheetDTO;
import com.abcgroep.crmsimulation.application.entities.Timesheet;
import com.abcgroep.crmsimulation.application.services.TimesheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/timesheets")
public class TimesheetController {
    @Autowired
    private TimesheetService timesheetService;




    @PostMapping
    public ResponseEntity<Timesheet> addTimesheet(@RequestBody TimesheetDTO timesheetDTO) {
        Timesheet newTimesheet = timesheetService.addTimesheet(timesheetDTO);
        return ResponseEntity.ok(newTimesheet);
    }
}
