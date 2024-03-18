package com.abcgroep.projectapi_simulation.integration.util.repositories;

import java.util.List;
import java.util.Map;
import java.sql.Timestamp;

public interface ExternalEntityRepository {
    List<Map<String, Object>> findAll();
    /*List<Map<String, Object>> findSinceId(Long lastMappedId);*/
    List<Map<String, Object>> findModifiedSince(Timestamp lastModifiedTime);

}
