package com.example.hotel_booking.repository;

import com.example.hotel_booking.model.Booking;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository {

    Booking save(Booking reserved, int roomId, int userId);

    Booking get(int id, int userId);

//    int getPrice(int id, int userId);

//    List<Booking> getAllBetween(LocalDate startDate, LocalDate endDate, int userId);

    List<Booking> getAllBetweenWithRooms(LocalDate startDate, LocalDate endDate);

    List<Booking> getAll(int userId);
}
