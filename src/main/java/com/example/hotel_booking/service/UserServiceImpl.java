package com.example.hotel_booking.service;

import com.example.hotel_booking.model.User;
import com.example.hotel_booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static com.example.hotel_booking.util.Util.correctEmail;
import static com.example.hotel_booking.util.ValidationUtil.checkNotFound;
import static com.example.hotel_booking.util.ValidationUtil.checkNotFoundWithId;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        user.setEmail(correctEmail(user.getEmail()));
        return repository.save(user);
    }

    @Override
    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        user.setEmail(correctEmail(user.getEmail()));
        checkNotFoundWithId(repository.save(user), user.getId());
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public User get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }

    @Override
    public User getByEmail(String email) {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(repository.getByEmail(correctEmail(email)), "email=" + email);
    }
}
