package com.abcgroep.crmsimulation.application.repositories;

import com.abcgroep.crmsimulation.application.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
}
