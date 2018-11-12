package com.example.hotel_booking.service;

import com.example.hotel_booking.model.User;
import com.example.hotel_booking.to.UserTo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {

    User create(User user);

    void update(User user);

    void update(UserTo userTo);

    void delete(int id);

    User get(int id);

    List<User> getAll();

    User getByEmail(String email);
}
