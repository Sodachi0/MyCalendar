package com.mycalendar.calendarmanager.exception;

public class ReservationNotFoundException extends RuntimeException{
    public ReservationNotFoundException(){super();}
    public ReservationNotFoundException(String message){
        super(message);
    }
}
