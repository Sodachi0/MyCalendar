package com.mycalendar.calendarmanager.presentation.rest.response;

import java.util.List;

public class CalendarDetailsResponseDTO {
    private final List<AvailabilityDTO> availabilities;
    private final List<ReservationDTO> reservations;

    public CalendarDetailsResponseDTO(List<AvailabilityDTO> availabilities, List<ReservationDTO> reservations){
        this.availabilities = availabilities;
        this.reservations = reservations;
    }

    public List<AvailabilityDTO> getAvailabilities() {
        return availabilities;
    }

    public List<ReservationDTO> getReservations() {
        return reservations;
    }
}
