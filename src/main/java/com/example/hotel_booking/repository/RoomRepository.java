package com.example.hotel_booking.repository;

import com.example.hotel_booking.model.Room;

import java.time.LocalDate;
import java.util.List;

public interface RoomRepository {
    Room save(Room room);

    boolean delete(int id);

    Room get(int id);

    List<Room> getAll();

    List<Room> getAllByAvailableBetween(LocalDate startDate, LocalDate endDate);

    List<Room> getAllByCategory(int category);
}
