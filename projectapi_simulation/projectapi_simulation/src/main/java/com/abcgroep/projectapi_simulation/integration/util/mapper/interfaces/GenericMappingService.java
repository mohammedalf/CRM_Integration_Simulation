package com.abcgroep.projectapi_simulation.integration.util.mapper.interfaces;

import java.util.List;


public interface GenericMappingService <T>{
    List<T> mapData();
    void saveAllMigratedData(List<T> data);
    boolean areEntitiesAlreadyMapped();
}
