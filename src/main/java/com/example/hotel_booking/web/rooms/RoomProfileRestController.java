package com.example.hotel_booking.web.rooms;

import com.example.hotel_booking.model.Room;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static com.example.hotel_booking.util.Util.DATE_PATTERN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = RoomProfileRestController.REST_URL)
public class RoomProfileRestController extends AbstractRoomRestController{
    static final String REST_URL = "/rest/profile/room";

    @GetMapping(value = "/available", produces = APPLICATION_JSON_VALUE)
    public List<Room> getAllByAvailable(
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = DATE_PATTERN) LocalDate startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = DATE_PATTERN) LocalDate endDate) {
        return super.getAllByAvailableBetween(startDate, endDate);
    }

    @GetMapping(value = "/category/{category}", produces = APPLICATION_JSON_VALUE)
    public List<Room> getAllByCategory(@PathVariable("category") int category) {
        return super.getAllByCategory(category);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Room get(@PathVariable("id") int id) {
        return super.get(id);
    }
}
