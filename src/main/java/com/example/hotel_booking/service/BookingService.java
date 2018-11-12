package com.example.hotel_booking.service;

import com.example.hotel_booking.model.Booking;

import java.util.List;

public interface BookingService {

    Booking create(Booking booking, int roomId, int userId);

    void update(Booking booking, int roomId, int userId);

    // booking for the hotel by id and userId
    Booking get(int id, int userId);

    // all bookings for the hotel by userId
    List<Booking> getAllByUser(int userId);

    // all bookings for the hotel
    List<Booking> getAll();

    Integer getTotalPrice(int id);
}
