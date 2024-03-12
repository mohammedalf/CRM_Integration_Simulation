package com.abcgroep.projectapi_simulation.integration.util.setup;

import com.abcgroep.projectapi_simulation.application.entities.Consultant;

import java.util.List;

public interface GenericStartupService <T>{
    void migrateDataOnStartup(T entity);

}
