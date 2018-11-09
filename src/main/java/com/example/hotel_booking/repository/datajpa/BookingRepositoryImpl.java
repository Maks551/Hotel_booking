package com.example.hotel_booking.repository.datajpa;

import com.example.hotel_booking.model.Booking;
import com.example.hotel_booking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class BookingRepositoryImpl implements BookingRepository {
    private final CrudBookingRepository crudRepository;

    @Autowired
    public BookingRepositoryImpl(CrudBookingRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    @Override
    public Booking save(Booking reserved, int userId) {
        return null;
    }

    @Override
    public Booking get(int id, int userId) {
        return null;
    }

    @Override
    public int getPrice(int id, int userId) {
        return 0;
    }

//    @Override
//    public List<Booking> getAllBetween(LocalDate startDate, LocalDate endDate, int userId) {
//        return null;
//    }

    @Override
    public List<Booking> getAllBetweenWithRooms(LocalDate startDate, LocalDate endDate) {
        return crudRepository.getAllBetweenWithRooms(startDate, endDate);
    }
}
