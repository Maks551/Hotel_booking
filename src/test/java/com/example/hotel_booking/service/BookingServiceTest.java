package com.example.hotel_booking.service;

import com.example.hotel_booking.AbstractServiceTest;
import com.example.hotel_booking.model.Booking;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static com.example.hotel_booking.util.BookingTestData.*;
import static com.example.hotel_booking.util.RoomTestData.ROOM_ID;
import static com.example.hotel_booking.util.UserTestData.USER_ID;

class BookingServiceTest extends AbstractServiceTest {
    @Autowired
    private BookingService service;

    @Test
    void create() {
        Booking newBooking = new Booking(null, LocalDate.of(2018, 12, 12), LocalDate.of(2018, 12, 13), true, false, 1900);
        Booking created = service.create(newBooking, ROOM_ID, USER_ID);
        newBooking.setId(created.getId());
        assertMatch(service.getAllByUser(USER_ID), BOOKING, BOOKING_5, BOOKING_6, newBooking);
    }

    @Test
    void update() {
        Booking updated = new Booking(BOOKING);
        updated.setStartDate(LocalDate.of(2018, 1, 1));
        updated.setEndDate(LocalDate.of(2018, 1, 2));
        service.update(updated, ROOM_ID, USER_ID);
        assertMatch(service.get(BOOKING_ID, USER_ID), updated);
    }

    @Test
    void get() {
        assertMatch(service.get(BOOKING_ID, USER_ID), BOOKING);
    }

    @Test
    void getAllByUser() {
        assertMatch(service.getAllByUser(USER_ID), BOOKING, BOOKING_5, BOOKING_6);
    }

    @Test
    void getAll() {
        assertMatch(service.getAll(), LIST_BOOKINGS);
    }

    @Test
    void getPrice() {
        assertMatch(service.getTotalPrice(BOOKING_ID), PRICE);
    }
}