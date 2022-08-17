package com.mycalendar.calendarmanager.presentation.rest.request;

public class CreateAvailabilityRequestDTO implements RequestDTOValidity{
    private final String start;
    private final String end;

    private final String username;

    private final String password;

    public CreateAvailabilityRequestDTO(String start, String end, String username, String password) {
        this.start = start;
        this.end = end;
        this.username = username;
        this.password = password;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean isComplete(){
        return start != null && !start.isEmpty()
                && end != null && !end.isEmpty()
                && username != null && !username.isEmpty()
                && password != null && !password.isEmpty();
    }
}
