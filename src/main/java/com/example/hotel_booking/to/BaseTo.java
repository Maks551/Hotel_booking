package com.example.hotel_booking.to;

import com.example.hotel_booking.HasId;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public abstract class BaseTo implements HasId {
    protected Integer id;

    public BaseTo(Integer id) {
        this.id = id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}
