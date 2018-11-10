package com.example.hotel_booking.service;

import com.example.hotel_booking.model.Booking;
import com.example.hotel_booking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository repository;

    @Autowired
    public BookingServiceImpl(BookingRepository repository) {
        this.repository = repository;
    }

    @Override
    public int getPrice(int id, int userId) {
        return 0;
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
        return repository.get(id, userId);
    }

    @Override
    public List<Booking> getAll(int userId) {
        return repository.getAll(userId);
    }
}
