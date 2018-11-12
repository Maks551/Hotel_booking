package com.example.hotel_booking.web.bookings;

import com.example.hotel_booking.model.Booking;
import com.example.hotel_booking.service.BookingService;
import com.example.hotel_booking.to.BookingTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static com.example.hotel_booking.util.BookingUtil.createNewFromTo;
import static com.example.hotel_booking.util.ValidationUtil.checkNew;
import static com.example.hotel_booking.web.SecurityUtil.authUserId;

@RestController
@RequestMapping(value = BookingRestController.REST_URL)
public class BookingRestController {
    static final String REST_URL = "/rest/profile/booking";
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final BookingService service;

    @Autowired
    public BookingRestController(BookingService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Booking get(@PathVariable("id") int id) {
        int userId = authUserId();
        log.info("get room {} for user {}", id, userId);
        return service.get(id, userId);
    }

    @GetMapping(value = "/all-my", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Booking> getAllWithRoomsByUser() {
        int userId = authUserId();
        log.info("get all booking with room by userId {}", userId);
        return service.getAllByUser(userId);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Booking> getAllWithRooms() {
        log.info("get all booking with room");
        return service.getAll();
    }

    @PostMapping(value = "/{roomId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Booking> createWithLocation(@Valid @RequestBody BookingTo bookingTo,
                                                      @PathVariable("roomId") int roomId) {
        checkNew(bookingTo);
        int userId = authUserId();
        Booking bookingNew = createNewFromTo(bookingTo);
        log.info("create booking {} for users {}", bookingNew, userId);
        Booking created = service.create(bookingNew, roomId, userId);

        URI uriOfNewResponse = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResponse).body(created);
    }

    @GetMapping(value = "/{id}/price", produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer getTotalPrice(@PathVariable("id") int id) {
        log.info("get price by Booking by id={}", id);
        return service.getTotalPrice(id);
    }
}
