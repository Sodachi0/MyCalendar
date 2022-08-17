package com.mycalendar.calendarmanager.domain.entity;

public class ReservationEntity {
    private Long id;
    private Long availabilityId;
    private String start;
    private String end;
    private String title;
    private String email;

    public ReservationEntity() {}

    public ReservationEntity(Long id, Long availabilityId,String start, String end, String title, String email) {
        this.id = id;
        this.availabilityId = availabilityId;
        this.start = start;
        this.end = end;
        this.title = title;
        this.email = email;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getAvailabilityId(){return availabilityId;}

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

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }
}
