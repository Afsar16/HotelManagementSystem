package com.afhotels.HMS.repo;

import com.afhotels.HMS.entity.Room;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface RoomRepository extends MongoRepository<Room, String> {

//    @Query("SELECT DISTINCT r.roomType FROM Room r")
    @Aggregation("{ $group: {_id: '$roomType'} }") //get all room types based on their uniqueness
    List<String> findDistinctRoomTypes();


//    @Query("SELECT r FROM Room r WHERE r.roomType LIKE %:roomType% AND r.id NOT IN (SELECT bk.room.id FROM Booking bk WHERE" +
//            "(bk.checkInDate <= :checkOutDate) AND (bk.checkOutDate >= :checkInDate))")
//    List<Room> findAvailableRoomsByDatesAndTypes(LocalDate checkInDate, LocalDate checkOutDate, String roomType);
    @Query("{'bookings': {$size:0 }}") //find room that has no bookings
    List<Room> findAllAvailableRooms();

//    @Query("SELECT r FROM Room r WHERE r.id NOT IN (SELECT b.room.id FROM Booking b)")
//    List<Room> getAllAvailableRooms();
    List<Room> findByRoomTypeLikeAndIdNotIn(String roomType, List<String> bookedRoomIds);
}