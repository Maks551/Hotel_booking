package com.example.hotel_booking.service;

import com.example.hotel_booking.AbstractServiceTest;
import com.example.hotel_booking.model.Role;
import com.example.hotel_booking.model.User;
import com.example.hotel_booking.util.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.example.hotel_booking.util.UserTestData.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserServiceTest extends AbstractServiceTest {

    @Autowired
    private UserService service;

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(1));
    }

    @Test
    void testGet() {
        assertMatch(service.get(USER_ID), USER);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(1));
    }

    @Test
    void getByEmail() {
        User actual = service.getByEmail(USER_EMAIL);
        assertMatch(actual, USER);
    }

    @Test
    void delete() {
        service.delete(USER_ID);
        assertMatch(service.getAll(), USER_2, USER_3, ADMIN);
    }

    @Test
    void create() {
        User newUser = new User(null, "Created", "created@gmail.com", "password", Role.ROLE_USER);
        User created = service.create(new User(newUser));
        newUser.setId(created.getId());
        assertMatch(newUser, created);
    }

    @Test
    void update() {
        User updated = new User(USER);
        updated.setName("Updated");
        updated.setEmail("updated@gmail.com");
        service.update(updated);
        assertMatch(service.get(USER_ID), updated);
    }

    @Test
    void getAll() {
        assertMatch(service.getAll(), USER_LIST);
    }
}