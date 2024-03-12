package com.abcgroep.projectapi_simulation.integration.util.mapper.interfaces;

import java.util.List;
import java.util.Map;

public interface GenericMappingService <T>{
    List<T> mapData();
    boolean isDataSaved();
    boolean areEntitiesAlreadyMapped();
}
