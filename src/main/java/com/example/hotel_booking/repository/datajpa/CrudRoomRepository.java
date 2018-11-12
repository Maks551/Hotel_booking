package com.example.hotel_booking.repository.datajpa;

import com.example.hotel_booking.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudRoomRepository extends JpaRepository<Room, Integer> {

    @Override
    @Transactional
    Room save(Room entity);

    @Modifying
    @Transactional
    @Query("DELETE FROM Room r WHERE r.id=:id")
    int delete(@Param("id") int id);

    @Override
    List<Room> findAll();
}
