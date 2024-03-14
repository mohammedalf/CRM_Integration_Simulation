package com.abcgroep.projectapi_simulation.integration.util.migration.interfaces;

import java.util.List;


public interface GenericMappingService <T>{
    List<T> mapData();
    void saveAllMigratedData(List<T> data);
    boolean areEntitiesAlreadyMapped();
    List<T> mapEntitysSinceLastId(Long lastMappedId);
}
