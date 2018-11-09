package com.example.hotel_booking.util;

import org.springframework.test.web.servlet.ResultMatcher;

import static com.example.hotel_booking.web.json.JsonUtil.writeValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class TestUtil {
    public static <T> ResultMatcher contentJson(T expected) {
        return content().json(writeValue(expected));
    }
}
