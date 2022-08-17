package com.mycalendar.calendarmanager.presentation.rest.request;

public class DeleteReservationRequestDTO implements RequestDTOValidity{
    private final Long reservationId;
    private final String email;

    public DeleteReservationRequestDTO(Long reservationId, String email){
        this.reservationId = reservationId;
        this.email = email;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public String getEmail() {
        return email;
    }


    @Override
    public boolean isComplete() {
        return reservationId != null
                && email != null && !email.isEmpty();
    }
}
