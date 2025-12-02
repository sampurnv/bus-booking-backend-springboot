package com.busbooking.service;

import com.busbooking.model.Booking;
import com.busbooking.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingService {
    
    private final BookingRepository bookingRepository;
    private final EmailService emailService;
    
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
    
    public Booking getBookingById(String id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));
    }
    
    public Booking getBookingByNumber(String bookingNumber) {
        return bookingRepository.findByBookingNumber(bookingNumber)
                .orElseThrow(() -> new RuntimeException("Booking not found with number: " + bookingNumber));
    }
    
    public Booking createBooking(Booking booking) {
        // Generate unique booking number
        booking.setBookingNumber("BUS" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        booking.setBookingDate(LocalDateTime.now());
        booking.setStatus(Booking.BookingStatus.CONFIRMED);
        
        // Check seat availability
        List<String> bookedSeats = getBookedSeats(booking.getBusId(), booking.getRouteId(), booking.getJourneyDate());
        for (String seat : booking.getSeatNumbers()) {
            if (bookedSeats.contains(seat)) {
                throw new RuntimeException("Seat " + seat + " is already booked");
            }
        }
        
        Booking savedBooking = bookingRepository.save(booking);
        log.info("Created booking: {}", savedBooking.getBookingNumber());
        
        // Send confirmation email
        emailService.sendBookingConfirmation(savedBooking);
        
        return savedBooking;
    }
    
    public Booking updateBooking(String id, Booking bookingDetails) {
        Booking booking = getBookingById(id);
        booking.setPaymentStatus(bookingDetails.getPaymentStatus());
        booking.setPaymentId(bookingDetails.getPaymentId());
        booking.setTransactionId(bookingDetails.getTransactionId());
        booking.setStatus(bookingDetails.getStatus());
        return bookingRepository.save(booking);
    }
    
    public Booking cancelBooking(String id) {
        Booking booking = getBookingById(id);
        booking.setStatus(Booking.BookingStatus.CANCELLED);
        booking.setCancellationDate(LocalDateTime.now());
        booking.setPaymentStatus("REFUNDED");
        
        Booking cancelledBooking = bookingRepository.save(booking);
        log.info("Cancelled booking: {}", booking.getBookingNumber());
        
        // Send cancellation email
        emailService.sendBookingCancellation(cancelledBooking);
        
        return cancelledBooking;
    }
    
    public List<Booking> getUserBookings(String userId) {
        return bookingRepository.findByUserId(userId);
    }
    
    public List<String> getBookedSeats(String busId, String routeId, LocalDate journeyDate) {
        List<Booking> bookings = bookingRepository.findByBusIdAndRouteIdAndJourneyDate(busId, routeId, journeyDate);
        return bookings.stream()
                .filter(b -> b.getStatus() == Booking.BookingStatus.CONFIRMED)
                .flatMap(b -> b.getSeatNumbers().stream())
                .collect(Collectors.toList());
    }
}