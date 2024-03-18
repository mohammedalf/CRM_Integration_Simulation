package com.abcgroep.crmsimulation.application.entities;

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
    private LocalDateTime modifiedOn;
    @PrePersist
    @PreUpdate
    private void onUpdate() {
        modifiedOn = LocalDateTime.now();
    }

    @ManyToMany(mappedBy = "consultants")
    @JsonIgnore
    private Set<Project> projects = new HashSet<>();

    public Consultant() {

    }

    public Consultant(String name, String email) {
        this.name = name;
        this.email = email;

        this.projects = new HashSet<>();
    }

    public LocalDateTime getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(LocalDateTime modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

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

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }
}
