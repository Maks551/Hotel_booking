package com.example.hotel_booking.util;

import com.example.hotel_booking.model.Booking;
import com.example.hotel_booking.to.BookingTo;

import java.time.temporal.ChronoUnit;

public class BookingUtil {
    public static final int PRICE_BREAKFAST = 200;
    public static final int PRICE_CLEANING = 150;

    private BookingUtil() {
    }

    public static Booking createNewFromTo(BookingTo newBooking) {
        return new Booking(null, newBooking.getStartDate(), newBooking.getEndDate(),
                newBooking.isBreakfast(), newBooking.isCleaning(), getAllPrice(newBooking));
    }

    private static int getAllPrice(BookingTo bookingTo) {
        int days = (int) bookingTo.getStartDate().until(bookingTo.getEndDate(), ChronoUnit.DAYS);
        int breakfastPriceForDay = bookingTo.isBreakfast() ? PRICE_BREAKFAST : 0;
        int cleaningPriceForDay = bookingTo.isCleaning() && days > 1 ? PRICE_CLEANING : 0;
        return (bookingTo.getAllPrice() + breakfastPriceForDay + cleaningPriceForDay) * days;
    }
}
