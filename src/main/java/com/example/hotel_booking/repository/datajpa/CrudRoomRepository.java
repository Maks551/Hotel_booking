package com.example.hotel_booking.repository.datajpa;

import com.example.hotel_booking.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudRoomRepository extends JpaRepository<Room, Integer> {

    @Override
    @Transactional
    Room save(Room entity);

    @Override
    List<Room> findAll();

//    @Transactional
//    @Query("SELECT r FROM Room r JOIN FETCH Booking ")
//    List<Room> getAllByAvailable(LocalDate startDate, LocalDate endDate);
}
