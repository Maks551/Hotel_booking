package com.example.hotel_booking.service;

import com.example.hotel_booking.model.Room;

import java.time.LocalDate;
import java.util.List;

public interface RoomService {

    //Create only from Admin
    Room create(Room room, int userId);

    //Create only from Admin
    void update(Room room, int userId);

    void delete(int id, int userId);

    Room get(int id);

    List<Room> getAll();

    List<Room> getAllByAvailableBetween(LocalDate startDate, LocalDate endDate);

    List<Room> getAllByCategory(int category);
}
