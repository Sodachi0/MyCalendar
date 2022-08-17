package com.mycalendar.calendarmanager.domain.entity;

public class AvailabilityEntity {
    private Long id;
    private String start;
    private String end;

    public AvailabilityEntity() {}

    public AvailabilityEntity(Long id, String start, String end){
        this.id = id;
        this.start = start;
        this.end = end;
    }

    public long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getStart(){
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end){
        this.end = end;
    }
}
