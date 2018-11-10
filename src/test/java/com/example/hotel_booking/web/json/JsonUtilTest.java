package com.example.hotel_booking.web.json;

import com.example.hotel_booking.model.Room;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.example.hotel_booking.util.RoomTestData.ROOM;
import static com.example.hotel_booking.util.RoomTestData.assertMatch;

class JsonUtilTest {

    @Test
    void testReadWriteValue() throws Exception {
        String json = JsonUtil.writeValue(ROOM);
        System.out.println(json);
        Room meal = JsonUtil.readValue(json, Room.class);
        assertMatch(meal, ROOM);
    }

    @Test
    void testReadWriteValues() throws Exception {
        String json = JsonUtil.writeValue(ROOM);
        System.out.println(json);
        List<Room> meals = JsonUtil.readValues(json, Room.class);
        assertMatch(meals, ROOM);
    }
}