package com.example.hotel_booking.web;

import com.example.hotel_booking.service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
