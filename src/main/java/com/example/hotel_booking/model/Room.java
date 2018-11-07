package com.example.hotel_booking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "rooms", uniqueConstraints = @UniqueConstraint(columnNames = "number", name = "rooms_number_idx"))
public class Room extends AbstractBaseEntity{
    @NotBlank
    private int number;
    @NotBlank
    private int category;
    @NotBlank
    private int price;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room")
    private List<Booking> bookings;

    // Additional options
        // breakfast order
    private boolean breakfast;
        // ordering to clean the room
    private boolean cleaning;

    public Room(Integer id, int number, int category, int price, boolean breakfast, boolean cleaning) {
        super(id);
        this.number = number;
        this.category = category;
        this.price = price;
        this.breakfast = breakfast;
        this.cleaning = cleaning;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", number=" + number +
                ", category=" + category +
                ", price=" + price +
                ", breakfast=" + breakfast +
                ", cleaning=" + cleaning +
                '}';
    }
}
