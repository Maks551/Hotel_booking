package com.example.hotel_booking.web.rooms;

import com.example.hotel_booking.model.Room;
import com.example.hotel_booking.service.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static com.example.hotel_booking.util.Util.getValidDates;
import static com.example.hotel_booking.util.ValidationUtil.assureIdConsistent;
import static com.example.hotel_booking.util.ValidationUtil.checkNew;
import static com.example.hotel_booking.web.SecurityUtil.authUserId;

public abstract class AbstractRoomRestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RoomService service;


    public List<Room> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public Room get(int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    public Room create(Room room) {
        int userId = authUserId();
        checkNew(room);
        log.info("create {} for user by id={}", room, userId);
        return service.create(room, userId);
    }

    public void delete(int id) {
        int userId = authUserId();
        log.info("delete {} for user by id={}", id, userId);
        service.delete(id, userId);
    }

    public void update(Room room, int id) {
        int userId = authUserId();
        assureIdConsistent(room, id);
        log.info("update {} for user {}", room, userId);
        service.update(room, userId);
    }

    public List<Room> getAllByAvailableBetween(LocalDate startDate, LocalDate endDate) {
        LocalDate[] validDates = getValidDates(startDate, endDate);
        LocalDate validStartDate = validDates[0];
        LocalDate validEndDate = validDates[1];

        log.info("get all available rooms between {} and {}", validStartDate, validEndDate);
        return service.getAllByAvailableBetween(validStartDate, validEndDate);
    }

    public List<Room> getAllByCategory(int category) {
        log.info("get all rooms by category {}", category);
        return service.getAllByCategory(category);
    }
}
