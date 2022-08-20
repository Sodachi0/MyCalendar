package com.mycalendar.calendarmanager.presentation.rest;

import com.mycalendar.calendarmanager.domain.entity.AvailabilityEntity;
import com.mycalendar.calendarmanager.domain.entity.ReservationEntity;
import com.mycalendar.calendarmanager.domain.service.AvailabilityService;
import com.mycalendar.calendarmanager.domain.service.ReservationService;
import com.mycalendar.calendarmanager.presentation.rest.request.CreateAvailabilityRequestDTO;
import com.mycalendar.calendarmanager.presentation.rest.request.CreateReservationRequestDTO;
import com.mycalendar.calendarmanager.presentation.rest.request.DeleteAvailabilityRequestDTO;
import com.mycalendar.calendarmanager.presentation.rest.request.DeleteReservationRequestDTO;
import com.mycalendar.calendarmanager.presentation.rest.response.AvailabilityDTO;
import com.mycalendar.calendarmanager.presentation.rest.response.CalendarDetailsResponseDTO;
import com.mycalendar.calendarmanager.presentation.rest.response.ReservationDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/calendar")
public class CalendarManagerEndpoint {
    private final AvailabilityService availabilityService;
    private final ReservationService reservationService;

    public CalendarManagerEndpoint(AvailabilityService availabilityService, ReservationService reservationService) {
        this.availabilityService = availabilityService;
        this.reservationService = reservationService;
    }

    private AvailabilityDTO toAvailabilityDTO(AvailabilityEntity availabilityEntity){
        return new AvailabilityDTO(availabilityEntity.getId(), availabilityEntity.getStart(), availabilityEntity.getEnd());
    }

    private ReservationDTO toReservationDTO(ReservationEntity reservationEntity){
        return new ReservationDTO(reservationEntity.getId(),reservationEntity.getAvailabilityId(),
                reservationEntity.getStart(), reservationEntity.getEnd(), reservationEntity.getTitle());
    }

    @GetMapping("/all")
    public ResponseEntity<CalendarDetailsResponseDTO> getCalendarDetails() {
        List<AvailabilityDTO> availabilityDTOList = new ArrayList<>();
        List<ReservationDTO> reservationDTOList = new ArrayList<>();
        availabilityService.findAllAvailabilities().forEach(e -> availabilityDTOList.add(toAvailabilityDTO(e)));
        reservationService.findAllReservations().forEach(e -> reservationDTOList.add(toReservationDTO(e)));
        CalendarDetailsResponseDTO calendarDetailsResponseDTO = new CalendarDetailsResponseDTO(availabilityDTOList, reservationDTOList);
        return new ResponseEntity<>(calendarDetailsResponseDTO, HttpStatus.OK);
    }

    @PostMapping("/create/availability")
    public ResponseEntity<AvailabilityDTO> createAvailability(@RequestBody CreateAvailabilityRequestDTO request){
        if(!request.isComplete())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if(!availabilityService.isAdmin(request.getUsername(), request.getPassword()))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        AvailabilityEntity availabilityEntity;
        try {
            availabilityEntity = availabilityService.createAvailability(request.getStart(), request.getEnd());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(toAvailabilityDTO(availabilityEntity), HttpStatus.OK);
    }

    @PostMapping("/delete/availability")
    public ResponseEntity<?> deleteAvailability(@RequestBody DeleteAvailabilityRequestDTO request){
        if(!request.isComplete())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if(!availabilityService.isAdmin(request.getUsername(), request.getPassword()))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        availabilityService.deleteAvailability(request.getAvailabilityId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/create/reservation")
    public ResponseEntity<ReservationDTO> createReservation(@RequestBody CreateReservationRequestDTO request){
        if(!request.isComplete())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        ReservationEntity reservationEntity;
        try {
            reservationEntity = reservationService.createReservation(request.getAvailabilityId(),
                    request.getStart(), request.getEnd(), request.getTitle(), request.getEmail());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(toReservationDTO(reservationEntity), HttpStatus.OK);
    }

    @PostMapping("/delete/reservation")
    public ResponseEntity<?> deleteReservation(@RequestBody DeleteReservationRequestDTO request){
        if(!request.isComplete())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        try {
            if (!reservationService.deleteReservation(request.getReservationId(), request.getEmail()))
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
