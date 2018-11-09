package com.example.hotel_booking.web;

import com.example.hotel_booking.model.User;
import com.example.hotel_booking.service.UserService;
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
@RequestMapping(value = UserRestController.REST_URL)
public class UserRestController {
    static final String REST_URL = "/rest/profile";
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final UserService service;

    @Autowired
    public UserRestController(UserService service) {
        this.service = service;
    }


    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public User get(@PathVariable("id") int id) {
        log.info("get user for {}", id);
        return service.get(id);
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<User> register(@Valid @RequestBody User user) {
        log.info("create {}", user);
        checkNew(user);
        User created = service.create(user);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
