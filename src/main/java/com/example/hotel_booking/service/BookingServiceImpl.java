package com.example.hotel_booking.service;

import com.example.hotel_booking.model.Booking;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Override
    public int getPrice(int id, int userId) {
        return 0;
    }

    @Override
    public Booking create(Booking booking, int roomId, int userId) {
        return null;
    }

    @Override
    public void update(Booking booking, int roomId, int userId) {

    }

    @Override
    public Booking get(int id, int userId) {
        return null;
    }

    @Override
    public List<Booking> getAll(int userId) {
        return null;
    }
}
