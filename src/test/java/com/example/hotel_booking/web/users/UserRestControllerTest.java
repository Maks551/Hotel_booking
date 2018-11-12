package com.example.hotel_booking.web.users;

import com.example.hotel_booking.AbstractControllerTest;
import com.example.hotel_booking.model.Role;
import com.example.hotel_booking.model.User;
import com.example.hotel_booking.service.UserService;
import com.example.hotel_booking.to.UserTo;
import com.example.hotel_booking.util.UserUtil;
import com.example.hotel_booking.web.json.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static com.example.hotel_booking.util.TestUtil.userHttpBasic;
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
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(USER));
    }

    @Test
    void testGetByEmail() throws Exception{
        mockMvc.perform(get(REST_URL + "by")
                .with(userHttpBasic(USER))
                .param("email", USER_EMAIL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(USER));
    }

    @Test
    void testRegister() throws Exception {
        UserTo createdTo = new UserTo(null, "createdUser", "created@gmail.com", "createdPass");

        mockMvc.perform(post(REST_URL + "register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(createdTo)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void testUpdate() throws Exception {
        UserTo updatedTo = new UserTo(USER_ID, "New name", "new.email@gmail.com", "new.password");
        mockMvc.perform(put(REST_URL)
                .with(userHttpBasic(USER))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updatedTo)))
                .andExpect(status().isNoContent());

        assertMatch(service.getByEmail("new.email@gmail.com"), UserUtil.updateFromTo(new User(USER), updatedTo));
    }
}