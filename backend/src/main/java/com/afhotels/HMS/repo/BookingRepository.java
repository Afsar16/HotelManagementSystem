package com.afhotels.HMS.repo;

import com.afhotels.HMS.entity.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends MongoRepository<Booking, String> {

    Optional<Booking> findByBookingConfirmationCode(String confirmationCode);

    @Query("{ 'checkInDate': {$lte: ?1 }, 'checkOutDate': { $gte: ?0 } }")

    List<Booking> findBookingByDateRange(LocalDate checkInDate, LocalDate checkOutDate);

}