package com.mycalendar.calendarmanager.presentation.rest.request;

public class CreateReservationRequestDTO implements RequestDTOValidity{
    private final Long availabilityId;
    private final String start;
    private final String end;
    private final String title;
    private final String email;

    public CreateReservationRequestDTO(Long availabilityId, String start, String end, String title, String email){
        this.availabilityId = availabilityId;
        this.start = start;
        this.end = end;
        this.title = title;
        this.email = email;
    }

    public Long getAvailabilityId() {
        return availabilityId;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public String getTitle() {
        return title;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean isComplete() {
        return availabilityId != null
                && start != null && !start.isEmpty()
                && end != null && !end.isEmpty()
                && title != null && !title.isEmpty()
                && email != null && !email.isEmpty();
    }
}
