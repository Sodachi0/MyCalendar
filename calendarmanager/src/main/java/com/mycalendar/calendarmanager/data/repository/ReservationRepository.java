package com.mycalendar.calendarmanager.data.repository;

import com.mycalendar.calendarmanager.data.model.AvailabilityModel;
import com.mycalendar.calendarmanager.data.model.ReservationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationModel, Long> {
    void deleteAllByAvailabilityId(Long availabilityId);
    List<AvailabilityModel> findAllByAvailabilityId(Long availabilityId);
}
