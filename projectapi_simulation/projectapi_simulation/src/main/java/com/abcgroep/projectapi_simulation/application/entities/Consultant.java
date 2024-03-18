package com.abcgroep.projectapi_simulation.application.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Consultant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    private Long externalConsultantId;
    private LocalDateTime lastModified;

    @ManyToMany(mappedBy = "consultants")
    @JsonIgnore
    private Set<Project> projects = new HashSet<>();

    public Consultant() {

    }

    public Consultant(String name, String email, Long externalConsultantId) {
        this.name = name;
        this.email = email;
        this.externalConsultantId = externalConsultantId;
        this.projects = new HashSet<>();
    }

/*    public Consultant(String name, String email) {
        this.name = name;
        this.email = email;

        this.projects = new HashSet<>();
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getExternalConsultantId() {
        return externalConsultantId;
    }

    public void setExternalConsultantId(Long externalConsultantId) {
        this.externalConsultantId = externalConsultantId;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "Consultant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", externalConsultantId=" + externalConsultantId +
                ", projects=" + projects +
                '}';
    }
}
