package com.example.hotel_booking.util;

import java.time.LocalDate;

public class Util {
    public static final String DATE_PATTERN = "yyyy-MM-dd";

    public static String correctEmail(String email) {
        return email.trim().toLowerCase();
    }

    /**
     * Get array valid dates for startDate and endDate
     * */
    public static LocalDate[] getValidDates(LocalDate startDate, LocalDate endDate) {
        LocalDate start, end;
        if (startDate == null && endDate == null) {
            start = LocalDate.now();
            end = start.plusDays(1);
        } else {
            start = startDate == null ? endDate.minusDays(1) : startDate;
            end = endDate == null ? startDate.plusDays(1) : endDate;
        }
        return new LocalDate[]{start, end};
    }
}
