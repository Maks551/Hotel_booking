package com.example.hotel_booking.service;

import com.example.hotel_booking.model.Booking;

import java.util.List;

public interface BookingService {

    Booking create(Booking booking, int roomId, int userId);

    void update(Booking booking, int roomId, int userId);
//
//    void delete(int id, int userId);

    Booking get(int id, int userId);

    // all bookings for user by userId
    List<Booking> getAll(int userId);

    int getPrice(int id, int userId);
}
