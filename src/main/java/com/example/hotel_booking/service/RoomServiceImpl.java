package com.example.hotel_booking.service;

import com.example.hotel_booking.model.Room;
import com.example.hotel_booking.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

import static com.example.hotel_booking.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository repository;

    @Autowired
    public RoomServiceImpl(RoomRepository repository) {
        this.repository = repository;
    }

    @Override
    public Room create(Room room, int userId) {
        Assert.notNull(room, "room must not be null");
        return repository.save(room, userId);
    }

    @Override
    public void update(Room room, int userId) {
        Assert.notNull(room, "room must not be null");
        checkNotFoundWithId(repository.save(room, userId), room.getId());
    }

    @Override
    public void delete(int id, int userId) {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    @Override
    public Room get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public List<Room> getAll() {
        return repository.getAll();
    }

    @Override
    public List<Room> getAllByAvailableBetween(LocalDate startDate, LocalDate endDate) {
        Assert.notNull(startDate, "startDate must not be null");
        Assert.notNull(endDate, "endDate  must not be null");
        return repository.getAllByAvailableBetween(startDate, endDate);
    }

    @Override
    public List<Room> getAllByCategory(int category) {
        return repository.getAllByCategory(category);
    }
}
