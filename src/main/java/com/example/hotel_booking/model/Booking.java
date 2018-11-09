package com.example.hotel_booking.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
    private Room room;

    public Booking(Booking booking) {
        this(booking.getId(), booking.getStartDate(), booking.getEndDate());
    }

    public Booking(Integer id, LocalDate startDate, LocalDate endDate) {
        super(id);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
