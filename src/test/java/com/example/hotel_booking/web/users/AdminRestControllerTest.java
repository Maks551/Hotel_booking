package com.example.hotel_booking.web.users;

import com.example.hotel_booking.AbstractControllerTest;
import com.example.hotel_booking.model.Role;
import com.example.hotel_booking.model.User;
import com.example.hotel_booking.service.UserService;
import com.example.hotel_booking.to.UserTo;
import com.example.hotel_booking.web.json.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static com.example.hotel_booking.util.TestUtil.userHttpBasic;
import static com.example.hotel_booking.util.UserTestData.*;
import static com.example.hotel_booking.util.UserTestData.ADMIN;
import static com.example.hotel_booking.util.UserTestData.USER_3;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AdminRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = AdminRestController.REST_URL + '/';

    @Autowired
    private UserService service;

    @Test
    void testGet() throws Exception{
        mockMvc.perform(get(REST_URL + USER_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(USER));
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(USER, USER_2, USER_3, ADMIN));
    }

    @Test
    void testGetByEmail() throws Exception{
        mockMvc.perform(get(REST_URL + "by")
                .with(userHttpBasic(ADMIN))
                .param("email", ADMIN_EMAIL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(ADMIN));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + USER_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNoContent());
        assertMatch(service.getAll(), USER_2, USER_3, ADMIN);
    }

    @Test
    void testCreate() throws Exception {
        UserTo createdTo = new UserTo(null, "createdUser", "created@gmail.com", "createdPass");

        mockMvc.perform(post(REST_URL)
                .with(userHttpBasic(ADMIN))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(createdTo)))
                .andExpect(status().isCreated())
                .andDo(print());

    }

    @Test
    void testUpdate() throws Exception {
        User updated = new User(ADMIN_ID, "New name", "new.email@gmail.com", "new.password", Role.ROLE_USER, Role.ROLE_ADMIN);

        mockMvc.perform(put(REST_URL + ADMIN_ID)
                .with(userHttpBasic(ADMIN))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());

        assertMatch(service.get(ADMIN_ID), updated);
    }
}