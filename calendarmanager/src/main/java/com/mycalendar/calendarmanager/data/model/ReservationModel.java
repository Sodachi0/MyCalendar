package com.mycalendar.calendarmanager.data.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class ReservationModel implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long availabilityId;
    private String start;
    private String end;
    private String title;
    private String email;

    public ReservationModel() {}

    public ReservationModel(Long availabilityId,String start, String end, String title, String email){
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
