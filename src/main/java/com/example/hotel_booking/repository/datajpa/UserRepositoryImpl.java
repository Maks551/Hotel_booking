package com.example.hotel_booking.repository.datajpa;

import com.example.hotel_booking.model.User;
import com.example.hotel_booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final CrudUserRepository crudRepository;

    @Autowired
    public UserRepositoryImpl(CrudUserRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    @Override
    public User save(User user) {
        return crudRepository.save(user);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public User get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getAll() {
        return crudRepository.findAll();
    }

    @Override
    public User getByEmail(String email) {
        return crudRepository.getByEmail(email);
    }
}
