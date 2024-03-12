package com.abcgroep.projectapi_simulation.application.controllers;


import com.abcgroep.projectapi_simulation.application.dtos.TimesheetDTO;
import com.abcgroep.projectapi_simulation.application.entities.Timesheet;
import com.abcgroep.projectapi_simulation.application.services.TimesheetService;
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
