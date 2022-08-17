package com.mycalendar.calendarmanager.presentation.rest.request;

public class DeleteAvailabilityRequestDTO implements RequestDTOValidity{
    private final Long availabilityId;
    private final String username;
    private final String password;

    public DeleteAvailabilityRequestDTO(Long availabilityId, String username, String password){
        this.availabilityId = availabilityId;
        this.username = username;
        this.password = password;
    }

    public Long getAvailabilityId() {
        return availabilityId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean isComplete(){
        return availabilityId != null
                && username != null && !username.isEmpty()
                && password != null && !password.isEmpty();
    }
}
