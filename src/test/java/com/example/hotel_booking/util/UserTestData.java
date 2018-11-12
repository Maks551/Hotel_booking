package com.example.hotel_booking.util;

import com.example.hotel_booking.model.Role;
import com.example.hotel_booking.model.User;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Arrays;
import java.util.List;

import static com.example.hotel_booking.model.AbstractBaseEntity.START_SEQ;
import static com.example.hotel_booking.web.json.JsonUtil.writeIgnoreProps;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class UserTestData {
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = USER_ID + 3;
    public static final String USER_EMAIL = "first@gmail.com";
    public static final String ADMIN_EMAIL = "admin@gmail.com";

    public static final User USER = new User(USER_ID, "User1", USER_EMAIL, "password1", Role.ROLE_USER);
    public static final User USER_2 = new User(USER_ID + 1, "User2", "second@gmail.com", "password2", Role.ROLE_USER);
    public static final User USER_3 = new User(USER_ID + 2, "User3", "third@gmail.com", "password3", Role.ROLE_USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin1", ADMIN_EMAIL, "password4", Role.ROLE_USER, Role.ROLE_ADMIN);

    public static final List<User> USER_LIST = Arrays.asList(USER, USER_2, USER_3, ADMIN);

    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "bookings", "password");
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("bookings", "password").isEqualTo(expected);
    }

    public static ResultMatcher contentJson(User... expected) {
        return content().json(writeIgnoreProps(Arrays.asList(expected), "password"));
    }

    public static ResultMatcher contentJson(User expected) {
        return content().json(writeIgnoreProps(expected, "password"));
    }
}
