package com.example.hotel_booking.web;

import com.example.hotel_booking.model.Booking;
import com.example.hotel_booking.service.BookingService;
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

import static com.example.hotel_booking.util.ValidationUtil.checkNew;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

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

    @PostMapping(value = "/{roomId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Booking> createWithLocation(@Valid @RequestBody Booking booking,
                                                      @PathVariable("roomId") int roomId) {
        checkNew(booking);
        int userId = SecurityUtil.authUserId();
        log.info("create restaurant {} for user {}", booking, userId);
        Booking created = service.create(booking, roomId, userId);

        URI uriOfNewResponse = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResponse).body(created);
    }

}
