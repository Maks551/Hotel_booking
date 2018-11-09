package com.example.hotel_booking.service;

import com.example.hotel_booking.model.Booking;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDate;

import static com.example.hotel_booking.util.BookingTestData.*;
import static com.example.hotel_booking.util.RoomTestData.ROOM_ID;
import static com.example.hotel_booking.util.UserTestData.USER_ID;

@SpringJUnitConfig(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
class BookingServiceTest {
    @Autowired
    private BookingService service;

    @Test
    void create() {
        Booking newBooking = new Booking(null, LocalDate.of(2018, 12, 12), LocalDate.of(2018, 12, 13));
        Booking created = service.create(newBooking, ROOM_ID, USER_ID);
        newBooking.setId(created.getId());
        assertMatch(service.getAll(USER_ID), BOOKING, BOOKING_5, BOOKING_6, newBooking);
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
    void getAll() {
        service.getAll(USER_ID).forEach(System.out::println);
    }
}