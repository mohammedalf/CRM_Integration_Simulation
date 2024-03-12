package com.abcgroep.projectapi_simulation.integration.util.repositories;

import java.util.List;
import java.util.Map;

public interface ExternalEntityRepository {
    List<Map<String, Object>> findAll();
    List<Map<String, Object>> findSinceId(Long lastMappedId);
}
