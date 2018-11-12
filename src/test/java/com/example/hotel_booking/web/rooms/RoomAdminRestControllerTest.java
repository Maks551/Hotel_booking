package com.example.hotel_booking.web.rooms;

import com.example.hotel_booking.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static com.example.hotel_booking.util.RoomTestData.ROOM;
import static com.example.hotel_booking.util.RoomTestData.ROOM_ID;
import static com.example.hotel_booking.util.TestUtil.contentJson;
import static com.example.hotel_booking.util.TestUtil.userHttpBasic;
import static com.example.hotel_booking.util.UserTestData.ADMIN;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RoomAdminRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = RoomAdminRestController.REST_URL + '/';

    @Test
    void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + ROOM_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(ROOM));
    }
}
