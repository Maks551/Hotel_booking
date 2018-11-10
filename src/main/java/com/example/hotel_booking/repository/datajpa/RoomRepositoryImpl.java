package com.example.hotel_booking.repository.datajpa;

import com.example.hotel_booking.model.Booking;
import com.example.hotel_booking.model.Role;
import com.example.hotel_booking.model.Room;
import com.example.hotel_booking.model.User;
import com.example.hotel_booking.repository.RoomRepository;
import com.example.hotel_booking.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RoomRepositoryImpl implements RoomRepository {
    private final CrudRoomRepository roomRepo;
    private final CrudBookingRepository bookingRepo;
    private final CrudUserRepository userRepo;

    @Autowired
    public RoomRepositoryImpl(CrudRoomRepository roomRepo, CrudBookingRepository bookingRepo, CrudUserRepository userRepo) {
        this.roomRepo = roomRepo;
        this.bookingRepo = bookingRepo;
        this.userRepo = userRepo;
    }

    @Override
    public Room save(Room room, int userId) {
        User user = userRepo.findById(userId).orElse(null);
        if (user == null || !user.getRoles().contains(Role.ROLE_ADMIN)) {
            throw new NotFoundException("user mast be admin");
        }
        return roomRepo.save(room);
    }

    @Override
    public List<Room> getAll() {
        return roomRepo.findAll();
    }

    @Override
    public boolean delete(int id, int userId) {
        User user = userRepo.findById(userId).orElse(null);
        if (user == null || !user.getRoles().contains(Role.ROLE_ADMIN)) {
            throw new NotFoundException("user mast be admin");
        }
        return roomRepo.delete(id) != 0;
    }

    @Override
    public Room get(int id) {
        return null;
    }

    @Override
    public List<Room> getAllByAvailableBetween(LocalDate startDate, LocalDate endDate) {
        List<Room> allRooms = getAll();
        List<Booking> bookingRoomsBetweenDates = bookingRepo.getAllBetweenWithRooms(startDate, endDate);
        List<Room> rooms = bookingRoomsBetweenDates.stream().map(Booking::getRoom).collect(Collectors.toList());

        allRooms.removeAll(rooms);
        return allRooms;
    }

    @Override
    public List<Room> getAllByCategory(int category) {
        return getAll().stream().filter(room -> room.getCategory()==category).collect(Collectors.toList());
    }
}
