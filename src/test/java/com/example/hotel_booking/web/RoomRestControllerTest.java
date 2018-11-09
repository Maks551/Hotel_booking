package com.example.hotel_booking.web;

import com.example.hotel_booking.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static com.example.hotel_booking.util.RoomTestData.*;
import static com.example.hotel_booking.util.TestUtil.contentJson;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RoomRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = RoomRestController.REST_URL + '/';

    @Test
    void testGetAllAvailableRooms() throws Exception{
        mockMvc.perform(get(REST_URL + "available")
                .param("startDate", "2018-11-08")
                .param("endDate", "2018-11-10"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(ROOM_LIST_FILTERED));
    }

    @Test
    void testGetAllByCategory() throws Exception {
        mockMvc.perform(get(REST_URL + "category/" + 1))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(ROOM_LIST_BY_CATEGORY));
    }
}