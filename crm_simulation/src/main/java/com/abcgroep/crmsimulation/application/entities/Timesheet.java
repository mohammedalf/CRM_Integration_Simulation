package com.abcgroep.crmsimulation.application.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Timesheet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "consultant_id")
    private Consultant consultant;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    private LocalDate date;
    private Integer hours;
    private String description;
    private LocalDateTime modifiedOn;
    @PrePersist
    @PreUpdate
    private void onUpdate() {
        modifiedOn = LocalDateTime.now();
    }

    public Timesheet() {

    }

    public Timesheet(Consultant consultant, Project project, LocalDate date, Integer hours, String description) {
        this.consultant = consultant;
        this.project = project;
        this.date = date;
        this.hours = hours;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Consultant getConsultant() {
        return consultant;
    }

    public LocalDateTime getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(LocalDateTime modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public void setConsultant(Consultant consultant) {
        this.consultant = consultant;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
