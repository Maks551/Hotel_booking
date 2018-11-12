package com.example.hotel_booking.web.rooms;

import com.example.hotel_booking.model.Room;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = RoomAdminRestController.REST_URL)
public class RoomAdminRestController extends AbstractRoomRestController{
    static final String REST_URL = "/rest/admin/room";

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Room get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Room> createWithLocation(@Valid @RequestBody Room room) {
        Room created = super.create(room);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Room room, @PathVariable("id") int id) {
        super.update(room, id);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }
}
