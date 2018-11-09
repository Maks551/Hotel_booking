package com.example.hotel_booking.service;

import com.example.hotel_booking.model.User;

import java.util.List;

public interface UserService {

    User create(User user);

    void update(User user);

    void delete(int id);

    User get(int id);

    List<User> getAll();

    User getByEmail(String email);
}
