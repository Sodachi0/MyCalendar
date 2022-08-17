package com.mycalendar.calendarmanager.domain.service;

import com.mycalendar.calendarmanager.data.model.AvailabilityModel;
import com.mycalendar.calendarmanager.data.model.ReservationModel;
import com.mycalendar.calendarmanager.data.repository.AvailabilityRepository;
import com.mycalendar.calendarmanager.data.repository.ReservationRepository;
import com.mycalendar.calendarmanager.domain.entity.ReservationEntity;
import com.mycalendar.calendarmanager.exception.AvailabilityNotFoundException;
import com.mycalendar.calendarmanager.exception.InvalidDateFormatException;
import com.mycalendar.calendarmanager.exception.InvalidIntervalException;
import com.mycalendar.calendarmanager.exception.ReservationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final AvailabilityRepository availabilityRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, AvailabilityRepository availabilityRepository) {
        this.reservationRepository = reservationRepository;
        this.availabilityRepository = availabilityRepository;
    }

    public ReservationEntity toReservationEntity(ReservationModel model){
        return new ReservationEntity(model.getId(),model.getAvailabilityId(),
                model.getStart(), model.getEnd(), model.getTitle(), model.getEmail());
    }

    public ReservationEntity createReservation(Long availabilityId,String start, String end, String title, String email)
            throws AvailabilityNotFoundException, InvalidDateFormatException, InvalidIntervalException {
        Optional<AvailabilityModel> modelOptional = availabilityRepository.findById(availabilityId);
        if(modelOptional.isEmpty())
            throw new AvailabilityNotFoundException();

        AvailabilityModel model = modelOptional.get();

        IntervalService.Interval reservationInterval = new IntervalService.Interval(start, end);
        IntervalService.Interval availabilityInterval = new IntervalService.Interval(model.getStart(), model.getEnd());
        if (!IntervalService.isContaining(reservationInterval, availabilityInterval))
            throw new InvalidIntervalException();

        return toReservationEntity(reservationRepository.save(new ReservationModel(availabilityId, start, end, title, email)));
    }

    public List<ReservationEntity> findAllReservations(){
        List<ReservationEntity> availabilityList = new ArrayList<>();
        reservationRepository.findAll().forEach(e -> availabilityList.add(toReservationEntity(e)));
        return availabilityList;
    }

    public boolean deleteReservation(Long id, String email) throws ReservationNotFoundException{
        Optional<ReservationModel> modelOptional = reservationRepository.findById(id);
        if(modelOptional.isEmpty())
            throw new ReservationNotFoundException();

        ReservationModel model = modelOptional.get();
        if (!model.getEmail().equals(email))
            return false;

        reservationRepository.delete(model);
        return true;
    }
}
