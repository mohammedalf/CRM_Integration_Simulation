package com.abcgroep.crmsimulation.application.services;

import com.abcgroep.crmsimulation.application.dtos.TimesheetDTO;
import com.abcgroep.crmsimulation.application.dtos.UpdateTimesheetDTO;
import com.abcgroep.crmsimulation.application.entities.Consultant;
import com.abcgroep.crmsimulation.application.entities.Project;
import com.abcgroep.crmsimulation.application.entities.Timesheet;
import com.abcgroep.crmsimulation.application.repositories.ConsultantRepository;
import com.abcgroep.crmsimulation.application.repositories.ProjectRepository;
import com.abcgroep.crmsimulation.application.repositories.TimesheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimesheetService {
    @Autowired
    private TimesheetRepository timesheetRepository;

    @Autowired
    private ConsultantRepository consultantRepository;
    @Autowired
    private ProjectRepository projectRepository;

    public Timesheet addTimesheet(TimesheetDTO timesheetDTO) {
        Consultant consultant = consultantRepository.findById(timesheetDTO.getConsultantId())
                .orElseThrow(() -> new RuntimeException("Consultant not found"));
        Project project = projectRepository.findById(timesheetDTO.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found"));

        Timesheet timesheet = new Timesheet();
        timesheet.setConsultant(consultant);
        timesheet.setProject(project);
        timesheet.setDate(timesheetDTO.getDate());
        timesheet.setHours(timesheetDTO.getHours());
        timesheet.setDescription(timesheetDTO.getDescription());

        return timesheetRepository.save(timesheet);
    }

    public Timesheet updateTimesheet(Long id, UpdateTimesheetDTO timesheetDTO){
        Timesheet timesheet=timesheetRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Timesheet not found with id" + id));
        timesheet.setHours(timesheetDTO.getHours());
        timesheet.setDescription(timesheetDTO.getDescription());
        return timesheetRepository.save(timesheet);
    }
}
