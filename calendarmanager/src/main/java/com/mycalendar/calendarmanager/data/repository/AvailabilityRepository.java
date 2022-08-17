package com.mycalendar.calendarmanager.data.repository;

import com.mycalendar.calendarmanager.data.model.AvailabilityModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailabilityRepository extends JpaRepository<AvailabilityModel, Long> {
}
