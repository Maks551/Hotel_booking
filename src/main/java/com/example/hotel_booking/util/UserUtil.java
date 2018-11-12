package com.example.hotel_booking.util;

import com.example.hotel_booking.model.Role;
import com.example.hotel_booking.model.User;
import com.example.hotel_booking.to.UserTo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

public class UserUtil {
    public static final int MAX_SIZE_EMAIL = 100;
    public static final int MIN_SIZE_PASSWORD = 5;
    public static final int MAX_SIZE_PASSWORD = 100;
    public static final int MAX_SIZE_NAME = 100;

    public static UserTo asTo(User user) {
        return new UserTo(user.getId(), user.getName(), user.getEmail(), user.getPassword());
    }

    public static User createNewFromTo(UserTo newUser) {
        return new User(null, newUser.getName(), newUser.getEmail().toLowerCase(), newUser.getPassword(), Role.ROLE_USER);
    }

    public static User updateFromTo(User user, UserTo userTo) {
        user.setName(userTo.getName());
        user.setEmail(userTo.getEmail());
        user.setPassword(userTo.getPassword());
        return user;
    }

    public static User prepareToSave(User user, PasswordEncoder passwordEncoder) {
        String password = user.getPassword();
        user.setPassword(StringUtils.isEmpty(password) ? password : passwordEncoder.encode(password));
        user.setEmail(user.getEmail().trim().toLowerCase());
        return user;
    }
}
