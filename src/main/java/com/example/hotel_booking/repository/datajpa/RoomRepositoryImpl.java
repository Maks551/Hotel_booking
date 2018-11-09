package com.example.hotel_booking.repository.datajpa;

import com.example.hotel_booking.model.Booking;
import com.example.hotel_booking.model.Room;
import com.example.hotel_booking.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RoomRepositoryImpl implements RoomRepository {
    private final CrudRoomRepository roomRepository;
    private final CrudBookingRepository bookingRepository;

    @Autowired
    public RoomRepositoryImpl(CrudRoomRepository roomRepository, CrudBookingRepository bookingRepository) {
        this.roomRepository = roomRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Room save(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public List<Room> getAll() {
        return roomRepository.findAll();
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Room get(int id) {
        return null;
    }

    @Override
    public List<Room> getAllByAvailableBetween(LocalDate startDate, LocalDate endDate) {
        List<Room> allRooms = getAll();
        List<Booking> bookingRoomsBetweenDates = bookingRepository.getAllBetweenWithRooms(startDate, endDate);
        List<Room> rooms = bookingRoomsBetweenDates.stream().map(Booking::getRoom).collect(Collectors.toList());

        allRooms.removeAll(rooms);
        return allRooms;
    }

    @Override
    public List<Room> getAllByCategory(int category) {
        return getAll().stream().filter(room -> room.getCategory()==category).collect(Collectors.toList());
    }
}
