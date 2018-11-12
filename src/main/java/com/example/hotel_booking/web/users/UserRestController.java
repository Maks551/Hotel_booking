package com.example.hotel_booking.web.users;

import com.example.hotel_booking.AuthorizedUser;
import com.example.hotel_booking.model.User;
import com.example.hotel_booking.to.UserTo;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static com.example.hotel_booking.util.UserUtil.createNewFromTo;
import static com.example.hotel_booking.web.SecurityUtil.authUserId;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = UserRestController.REST_URL)
public class UserRestController extends AbstractUserRestController{
    static final String REST_URL = "/rest/profile";

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public User get(@AuthenticationPrincipal AuthorizedUser authUser) {
        return super.get(authUser.getId());
    }

    @GetMapping(value = "/by", produces = APPLICATION_JSON_VALUE)
    public User getByEmail(@RequestParam("email") String email) {
        return super.getByMail(email);
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<User> register(@Valid @RequestBody UserTo userTo) {
        User created = super.create(createNewFromTo(userTo));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody UserTo userTo, @AuthenticationPrincipal AuthorizedUser authUser) {
        super.update(userTo, authUser.getId());
    }
}
