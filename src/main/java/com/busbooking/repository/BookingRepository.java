package com.busbooking.repository;

import com.busbooking.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends MongoRepository<Booking, String> {
    
    Optional<Booking> findByBookingNumber(String bookingNumber);
    
    List<Booking> findByUserId(String userId);
    
    List<Booking> findByBusIdAndRouteIdAndJourneyDate(String busId, String routeId, LocalDate journeyDate);
    
    List<Booking> findByStatus(Booking.BookingStatus status);
    
    List<Booking> findByPaymentStatus(String paymentStatus);
}