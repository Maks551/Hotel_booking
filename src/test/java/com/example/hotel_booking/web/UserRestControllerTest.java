package com.example.hotel_booking.web;

import com.example.hotel_booking.AbstractControllerTest;
import com.example.hotel_booking.model.Role;
import com.example.hotel_booking.model.User;
import com.example.hotel_booking.service.UserService;
import com.example.hotel_booking.web.json.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static com.example.hotel_booking.util.UserTestData.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = UserRestController.REST_URL + '/';

    @Autowired
    private UserService service;

    @Test
    void testGet() throws Exception{
        mockMvc.perform(get(REST_URL + USER_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(USER));
    }

    @Test
    void testCreate() throws Exception {
        User createdTo = new User(null, "createdUser", "created@gmail.com", "createdPass", Role.ROLE_USER);

        mockMvc.perform(post(REST_URL + "/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(createdTo)))
                .andExpect(status().isCreated())
                .andDo(print());

    }
}