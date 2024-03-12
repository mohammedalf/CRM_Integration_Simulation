package com.abcgroep.crmsimulation.application.dtos;

import java.time.LocalDate;

public class TimesheetDTO {

    private Long consultantId;
    private Long projectId;
    private LocalDate date;
    private Integer hours;
    private String description;

    public TimesheetDTO() {

    }

    public TimesheetDTO(Long consultantId, Long projectId, LocalDate date, Integer hours, String description) {
        this.consultantId = consultantId;
        this.projectId = projectId;
        this.date = date;
        this.hours = hours;
        this.description = description;
    }

    public Long getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(Long consultantId) {
        this.consultantId = consultantId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
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
