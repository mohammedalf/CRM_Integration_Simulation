package com.abcgroep.projectapi_simulation.integration.crm.msdynamics.mappers;

import com.abcgroep.projectapi_simulation.application.entities.Timesheet;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

public class TimesheetMapper {

    public static Timesheet mapRowToTimesheet(Map<String, Object> row) {
        Timesheet timesheet = new Timesheet();
        timesheet.setExternalTimesheetId((Long) row.get("id"));
        timesheet.setDescription((String) row.get("description"));
        timesheet.setHours((Integer) row.get("hours"));
        timesheet.setLastModified((LocalDateTime) row.get("modified_on"));

        Object dateObject = row.get("date");
        if (dateObject instanceof java.sql.Date) {
            java.sql.Date dateSql = (java.sql.Date) dateObject;
            LocalDate date = dateSql.toLocalDate();
            timesheet.setDate(date);
        } else if (dateObject instanceof String) {

            String dateString = (String) dateObject;
            LocalDate date = LocalDate.parse(dateString);
            timesheet.setDate(date);
        }



        return timesheet;
    }

}
