package com.example.hotel_booking.web;

import com.example.hotel_booking.model.Room;
import com.example.hotel_booking.service.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static com.example.hotel_booking.util.Util.DATE_PATTERN;
import static com.example.hotel_booking.util.Util.getValidDates;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = RoomRestController.REST_URL)
public class RoomRestController {
    static final String REST_URL = "/rest/profile/room";
    private final Logger log = LoggerFactory.getLogger(getClass());
    private RoomService service;

    @Autowired
    public RoomRestController(RoomService service) {
        this.service = service;
    }

    @GetMapping(value = "/available", produces = APPLICATION_JSON_VALUE)
    public List<Room> getAllByAvailable(
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = DATE_PATTERN) LocalDate startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = DATE_PATTERN) LocalDate endDate) {

        LocalDate[] validDates = getValidDates(startDate, endDate);
        LocalDate validStartDate = validDates[0];
        LocalDate validEndDate = validDates[1];

        log.info("get all available rooms between {} and {}", validStartDate, validEndDate);
        return service.getAllByAvailableBetween(validStartDate, validEndDate);
    }

    /**
     * @param category is a Room category (category > 0).
     * */
    @GetMapping(value = "/category/{category}", produces = APPLICATION_JSON_VALUE)
    public List<Room> getAllByCategory(@PathVariable("category") int category) {
        log.info("get all rooms by category {}", category);
        return service.getAllByCategory(category);
    }
}
