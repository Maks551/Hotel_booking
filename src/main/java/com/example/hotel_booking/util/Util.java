package com.example.hotel_booking.util;

public class Util {
    public static final String DATE_PATTERN = "yyyy-MM-dd";

    public static String correctEmail(String email) {
        return email.trim().toLowerCase();
    }
}
