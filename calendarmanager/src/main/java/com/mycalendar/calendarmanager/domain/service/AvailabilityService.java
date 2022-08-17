package com.mycalendar.calendarmanager.domain.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycalendar.calendarmanager.data.model.AvailabilityModel;
import com.mycalendar.calendarmanager.data.repository.AvailabilityRepository;
import com.mycalendar.calendarmanager.data.repository.ReservationRepository;
import com.mycalendar.calendarmanager.domain.entity.AvailabilityEntity;
import com.mycalendar.calendarmanager.exception.InvalidDateFormatException;
import com.mycalendar.calendarmanager.exception.InvalidIntervalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AvailabilityService {
    private final AvailabilityRepository availabilityRepository;
    private final ReservationRepository reservationRepository;

    private static class UserInfo{
        private final String username;
        private final String password;
        public UserInfo(String username, String password){
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }
    private final UserInfo adminInfo;

    @Autowired
    public AvailabilityService(AvailabilityRepository availabilityRepository, ReservationRepository reservationRepository) {
        this.availabilityRepository = availabilityRepository;
        this.reservationRepository = reservationRepository;
        UserInfo user;
        try {
            String adminInfoJson = new String(Files.readAllBytes(Paths.get("src/main/resources/adminInfo.json")));
            ObjectMapper mapper = new ObjectMapper();
            user = mapper.readValue(adminInfoJson, UserInfo.class);
        } catch (Exception e){
            user = new UserInfo("admin", "password");
            e.printStackTrace();
        }
        adminInfo = user;
    }

    public boolean isAdmin(String username, String password){
        return adminInfo.getUsername().equals(username) && adminInfo.getPassword().equals(password);
    }

    public AvailabilityEntity toAvailabilityEntity(AvailabilityModel model){
        return new AvailabilityEntity(model.getId(), model.getStart(), model.getEnd());
    }

    public AvailabilityEntity createAvailability(String start, String end)
            throws InvalidDateFormatException, InvalidIntervalException {

        IntervalService.Interval newInterval = new IntervalService.Interval(start, end);
        availabilityRepository.findAll().forEach(availability -> {
            IntervalService.Interval interval = new IntervalService.Interval(availability.getStart(), availability.getEnd());
            if (IntervalService.isOverlapping(newInterval, interval))
                throw new InvalidIntervalException();
        });

        return toAvailabilityEntity(availabilityRepository.save(new AvailabilityModel(start, end)));
    }

    public List<AvailabilityEntity> findAllAvailabilities(){
        List<AvailabilityEntity> availabilitiesList = new ArrayList<>();
        availabilityRepository.findAll().forEach(e -> availabilitiesList.add(toAvailabilityEntity(e)));
        return availabilitiesList;
    }

    /*
    public AvailabilityEntity updateAvailability(Long id, String start, String end)
            throws AvailabilityNotFoundException{
        Optional<AvailabilityModel> modelOptional =  availabilityRepository.findById(id);
        if(modelOptional.isEmpty())
            throw new AvailabilityNotFoundException();


        AvailabilityModel model = modelOptional.get();
        model.setStart(start);
        model.setEnd(end);
        return toAvailabilityEntity(model);
    }
     */

    public void deleteAvailability(Long id){
        availabilityRepository.deleteById(id);
        reservationRepository.deleteAllByAvailabilityId(id);
    }
}
