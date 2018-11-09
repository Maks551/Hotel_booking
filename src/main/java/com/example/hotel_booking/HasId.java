package com.example.hotel_booking;

public interface HasId {
    Integer getId();

    void setId(Integer id);

    default boolean isNew() {
        return getId() == null;
    }
}
