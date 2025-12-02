package com.busbooking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "bookings")
public class Booking {
    
    @Id
    private String id;
    
    private String bookingNumber; // Unique booking reference
    private String userId;
    private String busId;
    private String routeId;
    
    // Passenger Details
    private PassengerDetails passengerDetails;
    
    // Journey Details
    private LocalDate journeyDate;
    private String fromCity;
    private String toCity;
    private String boardingPoint;
    private String droppingPoint;
    
    // Seat Details
    private List<String> seatNumbers;
    private int numberOfSeats;
    
    // Payment Details
    private double totalFare;
    private String paymentStatus; // PENDING, COMPLETED, FAILED, REFUNDED
    private String paymentMethod; // STRIPE, RAZORPAY, CASH
    private String paymentId;
    private String transactionId;
    
    // Booking Status
    private BookingStatus status; // CONFIRMED, CANCELLED, COMPLETED
    
    @CreatedDate
    private LocalDateTime bookingDate;
    private LocalDateTime cancellationDate;
    
    public enum BookingStatus {
        CONFIRMED, CANCELLED, COMPLETED
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PassengerDetails {
        private String name;
        private String email;
        private String phone;
        private int age;
        private String gender;
    }
}