package com.example.hotel_booking.service;

import com.example.hotel_booking.AuthorizedUser;
import com.example.hotel_booking.model.User;
import com.example.hotel_booking.repository.UserRepository;
import com.example.hotel_booking.to.UserTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

import static com.example.hotel_booking.util.UserUtil.prepareToSave;
import static com.example.hotel_booking.util.UserUtil.updateFromTo;
import static com.example.hotel_booking.util.Util.correctEmail;
import static com.example.hotel_booking.util.ValidationUtil.checkNotFound;
import static com.example.hotel_booking.util.ValidationUtil.checkNotFoundWithId;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User create(User user) {
        Assert.notNull(user, "users must not be null");
        user.setEmail(correctEmail(user.getEmail()));
        return repository.save(user);
    }

    @Override
    public void update(User user) {
        Assert.notNull(user, "users must not be null");
        user.setEmail(correctEmail(user.getEmail()));
        checkNotFoundWithId(repository.save(user), user.getId());
    }

    @Transactional
    @Override
    public void update(UserTo userTo) {
        User user = updateFromTo(get(userTo.getId()), userTo);
        repository.save(prepareToSave(user, passwordEncoder));
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

    @Override
    public AuthorizedUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.getByEmail(email.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(user);
    }
}
