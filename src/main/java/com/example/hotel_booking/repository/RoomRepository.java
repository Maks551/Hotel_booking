package com.example.hotel_booking.repository;

import com.example.hotel_booking.model.Room;

import java.time.LocalDate;
import java.util.List;

public interface RoomRepository {
    Room save(Room room, int userId);

    boolean delete(int id, int userId);

    Room get(int id);

    List<Room> getAll();

    List<Room> getAllByAvailableBetween(LocalDate startDate, LocalDate endDate);

    List<Room> getAllByCategory(int category);
}
