package com.mycalendar.calendarmanager.exception;

public class AvailabilityNotFoundException extends RuntimeException{
    public AvailabilityNotFoundException(){super();}
    public AvailabilityNotFoundException(String message){
        super(message);
    }
}
