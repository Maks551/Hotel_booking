package com.example.hotel_booking.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static com.example.hotel_booking.util.Util.DATE_PATTERN;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "bookings", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "start_date"},
        name = "reserved_unique_user_datetime_idx"))
public class Booking extends AbstractBaseEntity{
    @NotNull
    @DateTimeFormat(pattern = DATE_PATTERN)
    @Column(name = "start_date", columnDefinition = "DATE DEFAULT now()")
    private LocalDate startDate;
    @NotNull
    @DateTimeFormat(pattern = DATE_PATTERN)
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    // Additional options
    // breakfast order
    @NotNull
    private boolean breakfast;
    // ordering to clean the room
    @NotNull
    private boolean cleaning;

    // all price with all additional options
    @NotBlank
    @Column(name = "all_price")
    private int allPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @BatchSize(size = 50)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
    private Room room;

    public Booking(Booking booking) {
        this(booking.getId(), booking.getStartDate(), booking.getEndDate(), booking.isBreakfast(), booking.isCleaning(), booking.getAllPrice());
    }

    public Booking(Integer id, LocalDate startDate, LocalDate endDate, boolean breakfast, boolean cleaning, int allPrice) {
        super(id);
        this.startDate = startDate;
        this.endDate = endDate;
        this.breakfast = breakfast;
        this.cleaning = cleaning;
        this.allPrice = allPrice;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", breakfast=" + breakfast +
                ", cleaning=" + cleaning +
                ", allPrice" + allPrice +
                '}';
    }
}
