package com.example.hotel_booking.repository.datajpa;

import com.example.hotel_booking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudBookingRepository extends JpaRepository<Booking, Integer> {

    @Override
    List<Booking> findAll();

    @Query("SELECT b FROM Booking b " +
            "WHERE b.startDate <= :startDate AND b.endDate > :startDate " +
            "OR b.startDate < :endDate AND b.endDate >= :endDate")
    List<Booking> getAllBetweenWithRooms(@Param("startDate") LocalDate startDate,
                                         @Param("endDate") LocalDate endDate);
}
