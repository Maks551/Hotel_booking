package com.example.hotel_booking.web.rooms;

import com.example.hotel_booking.AbstractControllerTest;
import com.example.hotel_booking.to.UserTo;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static com.example.hotel_booking.util.RoomTestData.*;
import static com.example.hotel_booking.util.TestUtil.contentJson;
import static com.example.hotel_booking.util.TestUtil.userHttpBasic;
import static com.example.hotel_booking.util.UserTestData.USER;
import static com.example.hotel_booking.web.json.JsonUtil.writeValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RoomProfileRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = RoomProfileRestController.REST_URL + '/';

    @Test
    void testGetAllAvailableRooms() throws Exception{
        mockMvc.perform(get(REST_URL + "available")
                .param("startDate", "2018-11-08")
                .param("endDate", "2018-11-10")
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(ROOM_LIST_FILTERED));
    }

    @Test
    void testGetAllByCategory() throws Exception {
        mockMvc.perform(get(REST_URL + "category/" + 1)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(ROOM_LIST_BY_CATEGORY));
    }

    @Test
    void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + ROOM_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(ROOM));
    }
}