package com.example.hotel_booking.to;

import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

import static com.example.hotel_booking.util.Util.DATE_PATTERN;

@Getter
public class BookingTo extends BaseTo {
    @NotNull
    @DateTimeFormat(pattern = DATE_PATTERN)
    private LocalDate startDate;
    @NotNull
    @DateTimeFormat(pattern = DATE_PATTERN)
    private LocalDate endDate;

    // Additional options
    // breakfast order
    @NotNull
    private boolean breakfast;
    // ordering to clean the room
    @NotNull
    private boolean cleaning;

    // room price without options
    @NotNull
    private int allPrice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingTo that = (BookingTo) o;
        return breakfast == that.breakfast &&
                cleaning == that.cleaning &&
                Objects.equals(id, that.id) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startDate, endDate, breakfast, cleaning);
    }

    @Override
    public String toString() {
        return "BookingTo{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", breakfast=" + breakfast +
                ", cleaning=" + cleaning +
                '}';
    }
}
