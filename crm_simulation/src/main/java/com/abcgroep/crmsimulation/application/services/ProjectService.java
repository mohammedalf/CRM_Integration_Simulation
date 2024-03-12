package com.abcgroep.crmsimulation.application.services;

import com.abcgroep.crmsimulation.application.dtos.ProjectDTO;
import com.abcgroep.crmsimulation.application.entities.Consultant;
import com.abcgroep.crmsimulation.application.entities.Project;
import com.abcgroep.crmsimulation.application.repositories.ConsultantRepository;
import com.abcgroep.crmsimulation.application.repositories.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProjectService {
    private static final Logger logger = LoggerFactory.getLogger(ProjectService.class);
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ConsultantRepository consultantRepository;

/*    public Project addProject(Project project) {

        return projectRepository.save(project);
    }*/
    public Project addProject(ProjectDTO projectDTO) {
        Project project = new Project();
        project.setName(projectDTO.getName());
        project.setDescription(projectDTO.getDescription());
        project.setStartDate(projectDTO.getStartDate());
        project.setEndDate(projectDTO.getEndDate());
        // Stel eventuele default waarden of voer validaties uit
    return projectRepository.save(project);
}
    public Project assignConsultantsToProject(Long projectId, List<Long> consultantIds) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        Set<Consultant> consultants = new HashSet<>();
        for (Long id : consultantIds) {
            Consultant consultant = consultantRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Consultant not found"));
            consultants.add(consultant);
        }
        project.setConsultants(consultants);
        return projectRepository.save(project);
    }
/*public Project assignConsultantsToProject(Long projectId, List<Long> consultantIds) {
    Project project = projectRepository.findById(projectId)
            .orElseThrow(() -> new RuntimeException("Project not found"));
    Set<Consultant> consultants = new HashSet<>();
    for (Long id : consultantIds) {
        Consultant consultant = consultantRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Consultant with ID {} not found", id);
                    return new RuntimeException("Consultant not found");
                });
        consultants.add(consultant);
    }
    project.setConsultants(consultants);
    return projectRepository.save(project);
}*/

}
