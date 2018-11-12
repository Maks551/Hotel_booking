package com.example.hotel_booking.util;

import com.example.hotel_booking.model.Room;

import java.util.Arrays;
import java.util.List;

import static com.example.hotel_booking.model.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;

public class RoomTestData {
    public static final int ROOM_ID = START_SEQ + 4;
    public static final int CATEGORY_FIRST = 1;

    public static final Room ROOM = new Room(ROOM_ID, 101, CATEGORY_FIRST, 1700);
    public static final Room ROOM_2 = new Room(ROOM_ID + 1, 102, CATEGORY_FIRST, 1650);
    public static final Room ROOM_3 = new Room(ROOM_ID + 2, 103, CATEGORY_FIRST + 1, 860);
    public static final Room ROOM_4 = new Room(ROOM_ID + 3, 104, CATEGORY_FIRST + 1, 900);
    public static final Room ROOM_5 = new Room(ROOM_ID + 4, 201, CATEGORY_FIRST, 2100);
    public static final Room ROOM_6 = new Room(ROOM_ID + 5, 202, CATEGORY_FIRST, 2400);
    public static final Room ROOM_7 = new Room(ROOM_ID + 6, 203, CATEGORY_FIRST + 1, 930);
    public static final Room ROOM_8 = new Room(ROOM_ID + 7, 204, CATEGORY_FIRST + 2, 450);

    public static final List<Room> ROOM_LIST = Arrays.asList(ROOM, ROOM_2, ROOM_3, ROOM_4, ROOM_5, ROOM_6, ROOM_7, ROOM_8);
    public static final List<Room> ROOM_LIST_FILTERED = Arrays.asList(ROOM, ROOM_2, ROOM_3, ROOM_5, ROOM_6, ROOM_8);
    public static final List<Room> ROOM_LIST_BY_CATEGORY = Arrays.asList(ROOM, ROOM_2, ROOM_5, ROOM_6);

    public static void assertMatch(Room actual, Room expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "bookings");
    }

    public static void assertMatch(Iterable<Room> actual, Room... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Room> actual, Iterable<Room> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("bookings").isEqualTo(expected);
    }
}
