package com.mycalendar.calendarmanager.presentation.rest.response;

public class ReservationDTO {
    private final Long id;
    private final Long availabilityId;
    private final String start;
    private final String end;

    private final String title;

    public ReservationDTO(Long id, Long availabilityId, String start, String end, String title){
        this.id = id;
        this.availabilityId = availabilityId;
        this.title = title;
        this.start = start;
        this.end = end;
    }

    public Long getId() {
        return id;
    }

    public Long getAvailabilityId() {
        return availabilityId;
    }

    public String getTitle() {
        return title;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }
}
