package com.example.hotel_booking.web.bookings;

import com.example.hotel_booking.AbstractControllerTest;
import com.example.hotel_booking.model.Booking;
import com.example.hotel_booking.web.json.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.time.LocalDate;

import static com.example.hotel_booking.util.BookingTestData.*;
import static com.example.hotel_booking.util.RoomTestData.ROOM_ID;
import static com.example.hotel_booking.util.TestUtil.contentJson;
import static com.example.hotel_booking.util.TestUtil.userHttpBasic;
import static com.example.hotel_booking.util.UserTestData.ADMIN;
import static com.example.hotel_booking.util.UserTestData.USER;
import static com.example.hotel_booking.util.UserTestData.USER_ID;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BookingRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = BookingRestController.REST_URL + "/";

    @Test
    void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + BOOKING_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(BOOKING));
    }

    @Test
    void testCreate() throws Exception {
        Booking created = new Booking(null, LocalDate.of(2018, 12, 12), LocalDate.of(2018, 12, 13), true, false, 1900);

        mockMvc.perform(post(REST_URL + ROOM_ID)
                .with(userHttpBasic(USER))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void testGetAllWithRoomsByUser() throws Exception {
        mockMvc.perform(get(REST_URL + "all-my")
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(BOOKING_LIST_BY_USER_1));
    }

    @Test
    void testGetAllWithRooms() throws Exception {
        mockMvc.perform(get(REST_URL + "all")
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(LIST_BOOKINGS));
    }
    @Test
    void testGetPrice() throws Exception {
        mockMvc.perform(get(REST_URL + BOOKING_ID + "/price")
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(PRICE));
    }
}