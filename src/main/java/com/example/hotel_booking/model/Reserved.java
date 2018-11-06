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

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "reserved_rooms", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "start_date"},
        name = "reserved_unique_user_datetime_idx"))
public class Reserved extends AbstractBaseEntity{
    @NotNull
    @DateTimeFormat(pattern = DATE_PATTERN)
    @Column(name = "start_date")
    private LocalDate startDate;
    @NotNull
    @DateTimeFormat(pattern = DATE_PATTERN)
    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    public Reserved(Integer id, LocalDate startDate, LocalDate endDate) {
        super(id);
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
