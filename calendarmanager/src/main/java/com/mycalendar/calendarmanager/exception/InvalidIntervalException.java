package com.mycalendar.calendarmanager.exception;

public class InvalidIntervalException extends RuntimeException{
    public InvalidIntervalException(){super();}
    public InvalidIntervalException(String message){
        super(message);
    }
}
