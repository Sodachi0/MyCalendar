package com.mycalendar.calendarmanager.presentation.rest.response;

public class AvailabilityDTO {
    private final Long id;
    private final String start;
    private final String end;

    public AvailabilityDTO(Long id, String start, String end){
        this.id = id;
        this.start = start;
        this.end = end;
    }

    public Long getId() {
        return id;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }
}
