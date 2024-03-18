package com.abcgroep.projectapi_simulation.application.repositories;



import com.abcgroep.projectapi_simulation.application.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
    @Query("SELECT MAX(s.externalProjectId) FROM Project s")
    Long findMaxExternalProjectId();
    Optional<Project> findByExternalProjectId(Long externalProjectId);
}
