package com.example.hotel_booking.service;

import com.example.hotel_booking.model.Booking;
import com.example.hotel_booking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static com.example.hotel_booking.util.ValidationUtil.checkNotFoundWithId;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository repository;

    @Autowired
    public BookingServiceImpl(BookingRepository repository) {
        this.repository = repository;
    }

    @Override
    public Booking create(Booking booking, int roomId, int userId) {
        Assert.notNull(booking, "booking must not be null");
        return repository.save(booking, roomId, userId);
    }

    @Override
    public void update(Booking booking, int roomId, int userId) {
        Assert.notNull(booking, "booking must not be null");
        repository.save(booking, roomId, userId);
    }

    /**
     * Get Booking with Room
     * */
    @Override
    public Booking get(int id, int userId) {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    @Override
    public List<Booking> getAllByUser(int userId) {
        return repository.getAllByUser(userId);
    }

    @Override
    public List<Booking> getAll() {
        return repository.getAll();
    }

    @Override
    public Integer getTotalPrice(int id) {
        return repository.getPrice(id);
    }
}
