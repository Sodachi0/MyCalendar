package com.mycalendar.calendarmanager.domain.service;

import com.mycalendar.calendarmanager.exception.InvalidDateFormatException;
import com.mycalendar.calendarmanager.exception.InvalidIntervalException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class IntervalService {
    public static class Interval{
        private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        private final Date start;
        private final Date end;
        public Interval(String start, String end) throws InvalidDateFormatException, InvalidIntervalException {
            try {
                this.start = dateFormat.parse(start);
                this.end = dateFormat.parse(end);
            } catch (Exception e){
                throw new InvalidDateFormatException();
            }
            // if start is after end
            if(this.start.compareTo(this.end) >= 0)
                throw new InvalidIntervalException();
        }

        public Interval(Date start, Date end) throws InvalidIntervalException{
            this.start = start;
            this.end = end;
            // if start is after end
            if(this.start.compareTo(this.end) >= 0)
                throw new InvalidIntervalException();
        }

        /*
        public static boolean isValidInterval(String start, String end) throws InvalidDateFormatException{
            Date startDate;
            Date endDate;
            try {
                startDate = dateFormat.parse(start);
                endDate = dateFormat.parse(end);
            } catch (Exception e){
                throw new InvalidDateFormatException();
            }
            return startDate.before(endDate);
        }

        public static boolean isValidInterval(Date start, Date end) {
            return start.before(end);
        }
         */

        public Date getStart() {
            return start;
        }

        public Date getEnd() {
            return end;
        }
    }

    public static boolean isContaining(Interval intervalContainer, Interval intervalContained){
        return intervalContainer.getStart().compareTo(intervalContained.getStart()) <= 0
                && intervalContainer.getEnd().compareTo(intervalContained.getEnd()) >= 0;
    }

    public static boolean isOverlapping(Interval interval1, Interval interval2){
        return interval1.getStart().before(interval2.getEnd())
                || interval1.getEnd().after(interval2.getStart());
    }

    public static boolean isDisjoint(Interval interval1, Interval interval2){
        return !isOverlapping(interval1, interval2);
    }
}
