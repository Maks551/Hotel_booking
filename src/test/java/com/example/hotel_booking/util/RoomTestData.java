package com.example.hotel_booking.util;

import com.example.hotel_booking.model.Room;

import java.util.Arrays;
import java.util.List;

import static com.example.hotel_booking.model.AbstractBaseEntity.START_SEQ;
import static com.example.hotel_booking.web.json.JsonUtil.writeIgnoreProps;
import static org.assertj.core.api.Assertions.assertThat;

public class RoomTestData {
    public static final int ROOM_ID = START_SEQ + 4;
    public static final int ROOM2_ID = ROOM_ID + 1;
    public static final int ROOM3_ID = ROOM_ID + 2;
    public static final int ROOM4_ID = ROOM_ID + 3;
    public static final int ROOM5_ID = ROOM_ID + 4;
    public static final int ROOM6_ID = ROOM_ID + 5;
    public static final int ROOM7_ID = ROOM_ID + 6;
    public static final int ROOM8_ID = ROOM_ID + 7;

    public static final int CATEGORY = 1;

    public static final Room ROOM = new Room(ROOM_ID, 101, 1, 1700, false, true);
    public static final Room ROOM_2 = new Room(ROOM2_ID, 102, 1, 1650, true, false);
    public static final Room ROOM_3 = new Room(ROOM3_ID, 103, 2, 860, false, false);
    public static final Room ROOM_4 = new Room(ROOM4_ID, 104, 2, 900, true, false);
    public static final Room ROOM_5 = new Room(ROOM5_ID, 201, 1, 2100, true, true);
    public static final Room ROOM_6 = new Room(ROOM6_ID, 202, 1, 2400, true, false);
    public static final Room ROOM_7 = new Room(ROOM7_ID, 203, 2, 930, false, true);
    public static final Room ROOM_8 = new Room(ROOM8_ID, 204, 3, 450, false, false);

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
