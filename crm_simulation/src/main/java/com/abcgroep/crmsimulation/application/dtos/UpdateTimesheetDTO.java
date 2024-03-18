package com.abcgroep.crmsimulation.application.dtos;

import java.time.LocalDate;

public class UpdateTimesheetDTO {


    private LocalDate date;
    private Integer hours;
    private String description;

    public UpdateTimesheetDTO() {

    }

    public UpdateTimesheetDTO(LocalDate date, Integer hours, String description) {
        this.date = date;
        this.hours = hours;
        this.description = description;
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
