package com.mycalendar.calendarmanager.data.repository;

import com.mycalendar.calendarmanager.data.model.ReservationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationModel, Long> {
    public void deleteAllByAvailabilityId(Long availabilityId);
}
