package com.abcgroep.projectapi_simulation.application.controllers;


import com.abcgroep.projectapi_simulation.application.dtos.ConsultantsToProjectDTO;
import com.abcgroep.projectapi_simulation.application.dtos.ProjectDTO;
import com.abcgroep.projectapi_simulation.application.entities.Project;
import com.abcgroep.projectapi_simulation.application.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<Project> addProject(@RequestBody ProjectDTO projectDTO) {
        Project newProject = projectService.addProject(projectDTO);
        return ResponseEntity.ok(newProject);
    }

    @PostMapping("/{projectId}/assign-consultants")
    public ResponseEntity<Project> assignConsultantsToProject(@PathVariable Long projectId, @RequestBody ConsultantsToProjectDTO consultantsToProjectDTO) {
        Project updatedProject = projectService.assignConsultantsToProject(projectId, consultantsToProjectDTO.getConsultantIds());
        return ResponseEntity.ok(updatedProject);
    }
}
