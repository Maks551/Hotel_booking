package com.example.hotel_booking.web.users;

import com.example.hotel_booking.model.User;
import com.example.hotel_booking.to.UserTo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static com.example.hotel_booking.util.UserUtil.createNewFromTo;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = AdminRestController.REST_URL)
public class AdminRestController extends AbstractUserRestController {
    static final String REST_URL = "/rest/admin";

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public User get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<User> getAll() {
        return super.getAll();
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @GetMapping(value = "/by", produces = APPLICATION_JSON_VALUE)
    public User getByEmail(@RequestParam("email") String email) {
        return super.getByMail(email);
    }

    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody User user, @PathVariable("id") int id) {
        super.update(user, id);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createWithLocation(@Valid @RequestBody UserTo userTo) {
        User created = super.create(createNewFromTo(userTo));

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
