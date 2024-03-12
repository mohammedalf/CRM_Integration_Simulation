package com.abcgroep.projectapi_simulation.application.dtos;

import java.util.List;

public class ConsultantsToProjectDTO {
    private List<Long> consultantIds;


    public ConsultantsToProjectDTO() {

    }

    public ConsultantsToProjectDTO(List<Long> consultantIds) {
        this.consultantIds = consultantIds;
    }

    public List<Long> getConsultantIds() {
        return consultantIds;
    }

    public void setConsultantIds(List<Long> consultantIds) {
        this.consultantIds = consultantIds;
    }
}
