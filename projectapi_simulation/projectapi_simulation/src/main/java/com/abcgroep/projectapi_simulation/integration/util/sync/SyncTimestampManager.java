package com.abcgroep.projectapi_simulation.integration.util.sync;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class SyncTimestampManager {
    private static final Map<String, LocalDateTime> lastModifiedTimes = new HashMap<>();

    public static LocalDateTime getLastModifiedTime(String entityType) {
        // Retourneert de opgeslagen tijd, of een standaardtijd als er nog geen tijd is opgeslagen
        return lastModifiedTimes.getOrDefault(entityType, LocalDateTime.MIN);
    }

    public static void updateLastModifiedTime(String entityType, LocalDateTime time) {
        lastModifiedTimes.put(entityType, time);
    }
}
