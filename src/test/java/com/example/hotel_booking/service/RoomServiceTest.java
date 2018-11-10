package com.example.hotel_booking.service;

import com.example.hotel_booking.AbstractServiceTest;
import com.example.hotel_booking.model.Room;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static com.example.hotel_booking.util.RoomTestData.*;

class RoomServiceTest extends AbstractServiceTest {

    @Autowired
    private RoomService service;

    @Test
    void testGetAllByAvailableBetween() throws Exception {
        List<Room> allByAvailableBetween = service.getAllByAvailableBetween(
                LocalDate.of(2018, 11, 8),
                LocalDate.of(2018, 11, 10));
        assertMatch(allByAvailableBetween, ROOM, ROOM_2, ROOM_3, ROOM_5, ROOM_6, ROOM_8);
    }

    @Test
    void testGetAllByCategory() throws Exception {
        assertMatch(service.getAllByCategory(CATEGORY), ROOM_LIST_BY_CATEGORY);
    }
}