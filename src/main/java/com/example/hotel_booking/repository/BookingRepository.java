package com.example.hotel_booking.repository;

import com.example.hotel_booking.model.Booking;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository {

    Booking save(Booking reserved, int roomId, int userId);

    Booking get(int id, int userId);

    List<Booking> getAllBetweenWithRooms(LocalDate startDate, LocalDate endDate);

    List<Booking> getAllByUser(int userId);

    List<Booking> getAll();
}
