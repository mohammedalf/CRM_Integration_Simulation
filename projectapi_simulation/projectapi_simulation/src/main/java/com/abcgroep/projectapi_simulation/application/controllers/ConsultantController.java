package com.abcgroep.projectapi_simulation.application.controllers;


import com.abcgroep.projectapi_simulation.application.dtos.ConsultantDTO;
import com.abcgroep.projectapi_simulation.application.dtos.ConsultantsToProjectDTO;
import com.abcgroep.projectapi_simulation.application.entities.Consultant;
import com.abcgroep.projectapi_simulation.application.entities.Project;
import com.abcgroep.projectapi_simulation.application.services.ConsultantService;
import com.abcgroep.projectapi_simulation.application.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultants")
public class ConsultantController {
    @Autowired
    private ConsultantService consultantService;

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public ResponseEntity<Consultant> addConsultant(@RequestBody ConsultantDTO consultantDTO){
        Consultant newConsultant = consultantService.addConsultant(consultantDTO);
        return ResponseEntity.ok(newConsultant);
    }

    @PostMapping("/{projectId}/assign-consultants")
    public ResponseEntity<Project> assignConsultantsToProject(@PathVariable Long projectId, @RequestBody ConsultantsToProjectDTO consultantsToProjectDTO) {
        Project updatedProject = projectService.assignConsultantsToProject(projectId, consultantsToProjectDTO.getConsultantIds());
        return ResponseEntity.ok(updatedProject);
    }
}
