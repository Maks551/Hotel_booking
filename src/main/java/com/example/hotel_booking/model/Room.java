package com.example.hotel_booking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;

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

    public Room(Integer id, int number, int category, int price) {
        super(id);
        this.number = number;
        this.category = category;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room that = (Room) o;
        return number == that.number &&
                category == that.category &&
                price == that.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, category, price);
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", number=" + number +
                ", category=" + category +
                ", price=" + price +
                '}';
    }
}
