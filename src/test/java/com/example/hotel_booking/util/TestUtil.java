package com.example.hotel_booking.util;

import com.example.hotel_booking.model.User;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import static com.example.hotel_booking.web.json.JsonUtil.writeValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class TestUtil {
    public static <T> ResultMatcher contentJson(T expected) {
        return content().json(writeValue(expected));
    }

    public static RequestPostProcessor userHttpBasic(User user) {
        return SecurityMockMvcRequestPostProcessors.httpBasic(user.getEmail(), user.getPassword());
    }
}
